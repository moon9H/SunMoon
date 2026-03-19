import { elements } from "./elements.js";
import {
  VIEWERS,
  getAvatarImagePath,
  getAvatarText,
  getMessageType
} from "./messages.js";

function formatMemoryDate(memoryDate) {
  if (!memoryDate) {
    return "-";
  }

  return String(memoryDate).replaceAll("-", ".");
}

function escapeHtml(value) {
  return String(value)
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#39;");
}

function renderDetailEmptyState(title, date, message) {
  elements.detailTitle.textContent = title;
  elements.detailDate.textContent = date;
  elements.messageContainer.innerHTML = `<div class="empty-state">${message}</div>`;
}

function getSelectedConversation(conversations, selectedConversationId) {
  return conversations.find(
    (conversation) => conversation.id === selectedConversationId
  );
}

function createConversationItem(conversation, selectedConversationId, onSelectConversation) {
  const messageCount = conversation.messageCount ?? 0;
  const listItem = document.createElement("li");
  listItem.className = "conversation-item";

  if (conversation.id === selectedConversationId) {
    listItem.classList.add("active");
  }

  const button = document.createElement("button");
  button.type = "button";
  button.className = "conversation-button";
  button.innerHTML = `
    <div class="conversation-title-row">
      <p class="conversation-title">${escapeHtml(conversation.title)}</p>
    </div>
    <div class="conversation-meta">
      <span class="conversation-message-count" aria-label="메시지 ${messageCount}개">
        <span class="conversation-message-icon" aria-hidden="true"></span>
        <span>${messageCount}</span>
      </span>
      <span class="conversation-date">${formatMemoryDate(conversation.memoryDate)}</span>
    </div>
  `;

  button.addEventListener("click", () => {
    onSelectConversation(conversation.id);
  });

  listItem.appendChild(button);

  return listItem;
}

function createAvatar(message) {
  const avatar = document.createElement("div");
  avatar.className = "message-avatar";
  avatar.setAttribute("aria-label", `${message.author} 프로필`);

  const avatarImagePath = getAvatarImagePath(message.author);
  const avatarImage = document.createElement("img");
  avatarImage.className = "message-avatar-image";
  avatarImage.alt = `${message.author} 프로필 사진`;
  avatarImage.src = avatarImagePath;

  const avatarFallback = document.createElement("span");
  avatarFallback.className = "message-avatar-fallback";
  avatarFallback.textContent = getAvatarText(message.author);

  avatarImage.addEventListener("load", () => {
    avatar.classList.add("has-image");
  });

  avatarImage.addEventListener("error", () => {
    avatar.classList.remove("has-image");
    avatarImage.removeAttribute("src");
  });

  avatar.appendChild(avatarImage);
  avatar.appendChild(avatarFallback);

  return avatar;
}

function createMessageRow(message, currentViewerId) {
  const messageType = getMessageType(message.author, currentViewerId);
  const row = document.createElement("div");
  row.className = `message-row ${messageType}`;

  const avatar = createAvatar(message);
  const content = document.createElement("div");
  content.className = "message-content";

  const author = document.createElement("div");
  author.className = "message-author";
  author.textContent = message.author;

  const bubbleRow = document.createElement("div");
  bubbleRow.className = "message-bubble-row";

  const bubble = document.createElement("article");
  bubble.className = "message-bubble";

  const text = document.createElement("p");
  text.className = "message-text";
  text.textContent = message.content;

  const time = document.createElement("div");
  time.className = "message-time";
  time.textContent = message.displayTime;

  bubble.appendChild(text);
  bubbleRow.appendChild(bubble);
  bubbleRow.appendChild(time);
  content.appendChild(author);
  content.appendChild(bubbleRow);

  row.appendChild(avatar);
  row.appendChild(content);

  return row;
}

export function renderViewerToggle(currentViewerId, onSelectViewer) {
  elements.viewerToggle.innerHTML = "";

  VIEWERS.forEach((viewer) => {
    const button = document.createElement("button");
    button.type = "button";
    button.className = "viewer-toggle-button";
    button.textContent = viewer.label;

    if (viewer.id === currentViewerId) {
      button.classList.add("active");
    }

    button.addEventListener("click", () => {
      onSelectViewer(viewer.id);
    });

    elements.viewerToggle.appendChild(button);
  });
}

export function renderConversationList({
  conversations,
  selectedConversationId,
  onSelectConversation
}) {
  elements.conversationList.innerHTML = "";
  elements.conversationCount.textContent = `${conversations.length}개`;

  conversations.forEach((conversation) => {
    const conversationItem = createConversationItem(
      conversation,
      selectedConversationId,
      onSelectConversation
    );

    elements.conversationList.appendChild(conversationItem);
  });
}

export function renderConversationDetail({
  conversations,
  conversationDetails,
  selectedConversationId,
  currentViewerId,
  isDetailLoading,
  detailLoadError
}) {
  if (conversations.length === 0) {
    renderDetailEmptyState(
      "대화가 아직 없습니다",
      "-",
      "표시할 대화 데이터가 없습니다."
    );
    return;
  }

  const selectedSummary = getSelectedConversation(
    conversations,
    selectedConversationId
  );

  if (!selectedSummary) {
    renderDetailEmptyState(
      "대화를 찾을 수 없습니다",
      "-",
      "표시할 대화가 없습니다."
    );
    return;
  }

  if (isDetailLoading) {
    renderDetailEmptyState(
      selectedSummary.title,
      formatMemoryDate(selectedSummary.memoryDate),
      "대화 내용을 불러오는 중입니다."
    );
    return;
  }

  if (detailLoadError) {
    renderDetailEmptyState(
      selectedSummary.title,
      formatMemoryDate(selectedSummary.memoryDate),
      "대화 상세 정보를 불러오지 못했습니다."
    );
    return;
  }

  const selectedConversation = conversationDetails[selectedConversationId];

  if (!selectedConversation) {
    renderDetailEmptyState(
      selectedSummary.title,
      formatMemoryDate(selectedSummary.memoryDate),
      "대화 내용을 준비하고 있습니다."
    );
    return;
  }

  elements.detailTitle.textContent = selectedConversation.title;
  elements.detailDate.textContent = formatMemoryDate(selectedConversation.memoryDate);
  elements.messageContainer.innerHTML = "";

  selectedConversation.messages
    .slice()
    .sort((left, right) => left.sequence - right.sequence)
    .forEach((message) => {
      const messageRow = createMessageRow(message, currentViewerId);
      elements.messageContainer.appendChild(messageRow);
    });
}

export function renderLoadError() {
  elements.conversationList.innerHTML = "";
  elements.conversationCount.textContent = "0개";

  renderDetailEmptyState(
    "데이터를 불러오지 못했습니다",
    "-",
    "REST API 응답을 불러오지 못했습니다.<br>서버 상태와 API 경로를 확인해주세요."
  );
}
