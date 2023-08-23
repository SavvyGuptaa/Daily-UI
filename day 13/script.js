const chatMessages = document.getElementById('chatMessages');
const messageInput = document.getElementById('messageInput');
const sendButton = document.getElementById('sendButton');

sendButton.addEventListener('click', sendMessage);

function sendMessage() {
  const messageText = messageInput.value;
  if (messageText.trim() === '') {
    return;
  }

  const messageElement = document.createElement('div');
  messageElement.className = 'message';
  messageElement.textContent = messageText;

  chatMessages.appendChild(messageElement);
  chatMessages.scrollTop = chatMessages.scrollHeight;

  messageInput.value = '';
}
