// bgtAI.js

// 定义 API Key
const API_KEY = 'sk-c08705943d6446b1a8181d65c012fb46'; // 请将此处替换为您的实际 API 密钥

/**
 * bgtAI - 封装通义千问 API 调用，并在请求失败时进行重试
 * @param {Array} messages - 聊天内容数组，每个元素包含角色和消息内容
 * @param {String} model - 模型名称 (默认 'qwen-plus')
 * @param {Number} maxRetries - 最大重试次数 (默认 3)
 * @param {Number} retryDelay - 重试延迟时间（毫秒） (默认 1000ms)
 * @returns {Promise<String>} - 返回包含 AI 回复内容的 Promise
 */
async function bgtAI(messages, model = 'qwen-plus', maxRetries = 3, retryDelay = 1000) {
  // 确保消息格式正确
  if (!Array.isArray(messages) || messages.length === 0) {
    throw new Error('messages 参数必须是非空数组。');
  }

  let attempt = 0;

  while (attempt <= maxRetries) {
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
      attempt++;
      console.error(`调用 bgtAI 失败，尝试第 ${attempt} 次:`, error);

      if (attempt > maxRetries) {
        throw new Error(`所有 ${maxRetries} 次重试均失败。`);
      }

      // 等待一段时间后重试，可以使用指数退避策略
      await new Promise(res => setTimeout(res, retryDelay * attempt));
    }
  }
}

export default bgtAI;
