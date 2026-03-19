export const VIEWERS = [
  {
    id: "Sun",
    label: "선영으로 보기"
  },
  {
    id: "Moon",
    label: "문규로 보기"
  }
];

const AUTHOR_AVATAR_PATH = {
  Sun: "/assets/images/profiles/sun.jpg",
  Moon: "/assets/images/profiles/moon.jpg"
};

const DISPLAY_NAME_BY_VIEWER_ID = {
  Sun: "선영",
  Moon: "문규"
};

export function getDefaultViewerId(initialViewerId) {
  const matchedViewer = VIEWERS.find((viewer) => viewer.id === initialViewerId);
  return matchedViewer ? matchedViewer.id : VIEWERS[0].id;
}

export function getMessageType(author, currentViewerId) {
  return author === currentViewerId ? "sent" : "received";
}

export function getDisplayName(viewerId) {
  return DISPLAY_NAME_BY_VIEWER_ID[viewerId] || viewerId;
}

export function getAvatarText(author) {
  return getDisplayName(author).trim().charAt(0) || "?";
}

export function getAvatarImagePath(author) {
  return AUTHOR_AVATAR_PATH[author] || "";
}
