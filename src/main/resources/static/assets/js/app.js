import { elements } from "./elements.js";
import { getDefaultViewerId } from "./messages.js";
import {
  renderConversationDetail,
  renderConversationList,
  renderViewerToggle,
  renderLoadError
} from "./render.js";

const initialViewerId = document.body.dataset.initialViewer;

const state = {
  conversations: [],
  conversationDetails: {},
  selectedConversationId: null,
  currentViewerId: getDefaultViewerId(initialViewerId),
  isDetailLoading: false,
  detailLoadError: false
};

function updateView() {
  document.body.dataset.viewer = state.currentViewerId;

  renderViewerToggle(state.currentViewerId, selectViewer);

  renderConversationList({
    conversations: state.conversations,
    selectedConversationId: state.selectedConversationId,
    onSelectConversation: selectConversation
  });

  renderConversationDetail({
    conversations: state.conversations,
    conversationDetails: state.conversationDetails,
    selectedConversationId: state.selectedConversationId,
    currentViewerId: state.currentViewerId,
    isDetailLoading: state.isDetailLoading,
    detailLoadError: state.detailLoadError
  });
}

async function loadConversationDetail(conversationId) {
  if (conversationId === null || state.conversationDetails[conversationId]) {
    return;
  }

  state.isDetailLoading = true;
  state.detailLoadError = false;
  updateView();

  try {
    const response = await fetch(`/api/conversations/${conversationId}`);

    if (!response.ok) {
      throw new Error("대화 상세 정보를 불러오지 못했습니다.");
    }

    state.conversationDetails[conversationId] = await response.json();
  } catch (error) {
    state.detailLoadError = true;
    console.error(error);
  } finally {
    state.isDetailLoading = false;
    updateView();
  }
}

async function selectConversation(conversationId) {
  state.selectedConversationId = conversationId;
  state.detailLoadError = false;
  updateView();
  await loadConversationDetail(conversationId);
}

function selectViewer(viewerId) {
  state.currentViewerId = viewerId;
  updateView();
}

function getRandomConversation() {
  if (state.conversations.length === 0) {
    return null;
  }

  const currentIndex = state.conversations.findIndex(
    (conversation) => conversation.id === state.selectedConversationId
  );

  let randomIndex = Math.floor(Math.random() * state.conversations.length);

  if (state.conversations.length > 1) {
    while (randomIndex === currentIndex) {
      randomIndex = Math.floor(Math.random() * state.conversations.length);
    }
  }

  return state.conversations[randomIndex];
}

function showRandomConversation() {
  const randomConversation = getRandomConversation();

  if (!randomConversation) {
    return;
  }

  selectConversation(randomConversation.id);
}

async function loadConversations() {
  try {
    const response = await fetch("/api/conversations");

    if (!response.ok) {
      throw new Error("대화 목록을 불러오지 못했습니다.");
    }

    state.conversations = await response.json();
    state.selectedConversationId =
      state.conversations.length > 0 ? state.conversations[0].id : null;

    updateView();

    if (state.selectedConversationId !== null) {
      await loadConversationDetail(state.selectedConversationId);
    }
  } catch (error) {
    renderLoadError();
    console.error(error);
  }
}

elements.randomMemoryButton.addEventListener("click", showRandomConversation);

loadConversations();
