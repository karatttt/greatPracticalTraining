// bgtAI.js

// 定义 API Key
const API_KEY = 'sk-c08705943d6446b1a8181d65c012fb46'; // 请将此处替换为您的实际 API 密钥

/**
 * bgtAI - 封装通义千问 API 调用
 * @param {Array} messages - 聊天内容数组，每个元素包含角色和消息内容
 * @param {String} model - 模型名称 (默认 'qwen-plus')
 * @returns {Promise<String>} - 返回包含 AI 回复内容的 Promise
 */
async function bgtAI(messages, model = 'qwen-plus') {
  // 确保消息格式正确
  if (!Array.isArray(messages) || messages.length === 0) {
    throw new Error('messages 参数必须是非空数组。');
  }

  try {
    const response = await fetch('/qwen-api/compatible-mode/v1/chat/completions', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${API_KEY}`, // 自动包含 API Key
      },
      body: JSON.stringify({
        model, // 指定模型
        messages, // 传入聊天内容
      }),
    });

    if (!response.ok) {
      throw new Error(`API 请求失败，状态码: ${response.status}`);
    }

    const data = await response.json();
    if (data.choices && data.choices[0]?.message?.content) {
      return data.choices[0].message.content; // 返回 AI 回复内容
    } else {
      throw new Error('未收到有效的 AI 回复内容。');
    }
  } catch (error) {
    console.error('调用 bgtAI 失败:', error);
    throw error;
  }
}

export default bgtAI;
