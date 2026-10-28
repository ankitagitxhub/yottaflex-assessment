# Automated Enterprise HR Policy Resolution & Validation Data Engine

A standalone, deterministic data engineering framework optimized to build, simulate, and validate complex relational transaction histories modeling enterprise HR environments.

## 📊 Complete Data Dictionary

The generation pipeline synthesizes and structures 11 relational entities to map end-to-end employee lifecycles:

*   **Department**: Core structural divisions (`department_id`, `department_name`). Cardinality: 1:N with Employees.
*   **Employee**: Corporate personnel entries (`employee_id`, `name`, `department_id`, `clearance_level`). 
*   **PolicyDocument**: Master records of corporate structural guidelines (`policy_id`, `title`, `category`). Cardinality: 1:N with Versions.
*   **PolicyVersion**: Historical version snapshots (`version_id`, `policy_id`, `version_number`, `effective_date`, `status`).
*   **KnowledgeArticle**: Searchable support items mapping answers (`article_id`, `title`, `associated_version_id`).
*   **Ticket**: System case entities tracking inbound employee queries (`ticket_id`, `employee_id`, `category`, `priority`, `status`, `sla_due_hours`, `created_at`). Cardinality: 1:N with Messages.
*   **Message**: Communication logs recording ticket dialogues (`message_id`, `ticket_id`, `sender_role`, `content`, `timestamp`).
*   **Category / Assignment / SLA / Resolution**: Helper bridge metrics mapping target business states, priority rules, and operational handling outcomes.

## 🚀 Execution Instructions

### Prerequisites
* Java Development Kit (JDK) 8 or higher installed on your host system.

### How to Run and Verify
1. Open your system's terminal or command window and step into the project folder:
   ```bash
   cd yottaflex-assessment
   ```
2. Compile the updated data pipeline source code:
   ```bash
   javac src/Main.java
   ```
3. Execute the binary target payload to output all seed data arrays locally:
   ```bash
   java src.Main
   ```
4. Verify results inside the newly populated `/data` directory files.

## 🧠 Engineering Decisions, Assumptions & Anomaly Mappings

### Key Design Assumptions
* **Isolated Environment:** Assumed processing must execute independent of cloud servers or external configuration layers to support clean deterministic evaluations.
* **Traceable Lineage:** Chose explicit immutable text formats to guarantee state changes are fully audit-logged without rewriting operational tables.

### Explicit Anomaly Tracking (Ground Truth Strategy)
The pipeline isolates 4 required negative exception paths, writing metrics explicitly to `data/ground_truth.csv`:
1.  **OBSOLETE_POLICY_CITATION:** Tracks system resolutions incorrectly calling historical inactive versions (`VER-...-01`).
2.  **MISROUTED_SENSITIVE_CASE:** Flags confidential employee-relations instances leaking out into standard queues.
3.  **SLA_CALCULATION_ERROR:** Isolates workflow timing loops where core business operational hours are calculated incorrectly.
4.  **UNSUPPORTED_POLICY_CLAIM:** Exposes user validation failures where completely unverified logic fields are asserted as confirmed corporate policy facts.