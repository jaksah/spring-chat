# Spring Chat
Simple application for generating a SQL query from user input based on a database schema.
Core technologies used:
- Kotlin
- Spring Boot
- [LangChain4j](https://docs.langchain4j.dev/)
- Next.js

## Requirements / Developed with
- Node.js v20.10.0
- [Ollama](https://ollama.com/) 0.1.32
- Java 21

## How to run
1. Clone the repository
1. Run `npm install --prefix frontend` from root directory 
1. Start frontend with `npm start --prefix frontend`
1. Start backend with `./backend/gradlew run -p backend`
1. Start Ollama with `ollama run llama3`
1. Open `http://localhost:3000` in your browser