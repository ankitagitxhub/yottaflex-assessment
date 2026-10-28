# YottaFlex Local Processing Engine

A lightweight, standalone backend processing engine designed to parse, validate, and summarize raw inbound data metrics efficiently.

## 🚀 Execution Instructions

### Prerequisites
* Java Development Kit (JDK) 8 or higher installed on your local host system.

### How to Build and Run
1. Open your computer's terminal or command prompt window.
2. Navigate directly into the root folder:
   ```bash
   cd yottaflex-assessment
   ```
3. Compile the Java source file cleanly:
   ```bash
   javac src/Main.java
   ```
4. Execute the compiled application bytecode payload:
   ```bash
   java src.Main
   ```

## 🧠 Engineering Decisions & Assumptions

### Key Design Assumptions
* **Isolated Environment:** Assumed the assessment should run entirely on local computing environments without requiring expensive cloud accounts or external databases.
* **Stream Failures:** Assumed individual structural anomalies in data inputs should be caught and logged isolatedly rather than throwing errors that terminate the entire processing pipeline.

### Architectural Trade-offs
* **In-Memory Storage:** Chose simple in-memory dynamic arrays (`ArrayList`) over persistent disk writing. This ensures lightning-fast execution times and zero complex installation setups for the reviewer, though data resets cleanly when execution finishes.
