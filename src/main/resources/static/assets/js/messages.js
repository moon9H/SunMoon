export const VIEWERS = [
  {
    id: "선영",
    label: "선영으로 보기"
  },
  {
    id: "문규",
    label: "문규로 보기"
  }
];

const AUTHOR_AVATAR_PATH = {
  선영: "/assets/images/profiles/sunyoung.jpg",
  문규: "/assets/images/profiles/moongyu.jpg"
};

export function getDefaultViewerId(initialViewerId) {
  const matchedViewer = VIEWERS.find((viewer) => viewer.id === initialViewerId);
  return matchedViewer ? matchedViewer.id : VIEWERS[0].id;
}

export function getMessageType(author, currentViewerId) {
  return author === currentViewerId ? "sent" : "received";
}

export function getAvatarText(author) {
  return author ? author.trim().charAt(0) : "?";
}

export function getAvatarImagePath(author) {
  return AUTHOR_AVATAR_PATH[author] || "";
}
