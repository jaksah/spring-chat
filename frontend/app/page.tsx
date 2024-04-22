'use client';

import React, { useState } from 'react';

export default function Home() {
  const [input, setInput] = useState(''); // To store the textarea input
  const [response, setResponse] = useState(''); // To store the server response

  // Handles the textarea change
  const handleInputChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setInput(e.target.value);
  };

  // Handles the button click to send the POST request
  const handleSendClick = async () => {
    try {
      const res = await fetch('http://localhost:8080/chat', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ message: input }),
      });
      const data = await res.json();
      setResponse(data.message); // Assuming the response is the data you want to display
    } catch (error) {
      console.error('Error sending the request:', error);
      setResponse('Failed to fetch data.'); // Handling error by setting response to a default message
    }
  };

  return (
    <main className="flex min-h-screen flex-col items-center p-24">
      <div className="flex place-items-center">
        <div>
          <textarea
            className="w-full h-32 p-4 text-sm text-gray-700 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
            placeholder="Type something..."
            value={input}
            onChange={handleInputChange}
          ></textarea>
          <button
            className="text-white bg-blue-700 border-0 py-2 px-8 focus:outline-none hover:bg-blue-600 rounded text-lg"
            onClick={handleSendClick}
          >
            Send
          </button>
        </div>
      </div>
      <div className="markdown">
        <p className="text-gray-700 dark:text-gray-300">
          <code>
            {response} {/* Displaying the response here */}
          </code>
        </p>
      </div>
    </main>
  );
}
