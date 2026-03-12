Below is a **complete PI-level Markdown report** fitted exactly into your **given template structure (Sections 1–10)**.
It integrates the **actual observations from your uploaded notes**, such as infeasible loops and tool limitations. 

You can **paste this directly into your Markdown report**.

---

# SENG 637 - Dependability and Reliability of Software Systems

## Lab Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation

| Group #       |   |
| ------------- | - |
| Student Names |   |
|               |   |
|               |   |
|               |   |

(Note that some labs require individual reports while others require one report for each group.)

---

# 1 Introduction

Software testing plays a central role in ensuring the dependability and reliability of complex software systems. As systems grow in size and complexity, manually verifying all behaviors becomes infeasible. Therefore, systematic testing techniques and coverage criteria are used to evaluate whether test suites adequately exercise program behavior.

The objective of this lab was to evaluate the adequacy of unit tests using several structural testing criteria, including **line coverage, branch coverage, method coverage, and data-flow coverage**. The subject system used for experimentation was the **JFreeChart library**, specifically focusing on the `DataUtilities` class.

The lab consisted of the following major tasks:

* Designing new unit tests to improve coverage
* Identifying unreachable code paths
* Performing manual **data-flow analysis**
* Measuring coverage using automated coverage tools
* Evaluating the strengths and limitations of different coverage metrics

The results provide insights into how structural testing criteria can guide the design of more effective test suites and improve the reliability of software components.

---

# 2 Manual data-flow coverage calculations for X and Y methods

Manual data-flow testing was conducted for the following methods:

* `calculateColumnTotal`
* `calculateRowTotal`

Data-flow testing focuses on analyzing the relationships between **variable definitions (def)** and **variable uses (use)** throughout the control flow of a program.

## Data-flow analysis of calculateColumnTotal

The method computes the total of values in a specified column of a dataset.

Key variables involved include:

* `data`
* `column`
* `rowCount`
* `total`
* loop variables (`r`, `r2`)
* intermediate values (`n1`, `n2`)

The following **definition-use relationships** were identified.

| Statement | Definition   | Use              |
| --------- | ------------ | ---------------- |
| 123       | data, column |                  |
| 124       |              | data             |
| 125       | total        |                  |
| 126       | rowCount     | data             |
| 127       | r            | r, rowCount      |
| 128       | n1           | data, r, column  |
| 129       |              | n1               |
| 130       | total        | total, n1        |
| 133       | r2           | r2, rowCount     |
| 134       | n2           | data, r2, column |
| 135       |              | n2               |
| 136       | total        | total, n2        |

Each **definition-use pair** represents a potential path that should be exercised by test cases.

## Infeasible Paths

During analysis, certain paths were identified as **infeasible**.

For example, the second loop in `calculateColumnTotal` has the condition:

```
for (int r2 = 0; r2 > rowCount; r2++)
```

Since `rowCount` is always non-negative, this loop condition cannot evaluate to true. Therefore, the entire loop body is unreachable. 

Similar infeasible paths were observed in:

* `calculateRowTotal`
* `getCumulativePercentages`

Because these paths are unreachable, they cannot be covered by any test case.

---

# 3 A detailed description of the testing strategy for the new unit test

The testing strategy followed a **coverage-driven test generation approach**.

The process consisted of the following steps:

1. **Initial coverage analysis**

   Existing tests were executed to identify untested statements and branches.

2. **Control flow inspection**

   The source code was manually analyzed to identify:

   * decision points
   * loop conditions
   * null checks
   * boundary conditions

3. **Targeting uncovered branches**

   Additional tests were designed specifically to execute uncovered branches.

4. **Edge case generation**

   Particular attention was given to edge cases including:

   * null dataset values
   * empty datasets
   * minimal dataset sizes

5. **Iterative coverage validation**

   After adding new test cases, coverage metrics were recalculated to confirm improvements.

This strategy ensured that the new unit tests were **systematically designed rather than randomly generated**, allowing them to maximize coverage improvements.

---

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Five representative test cases were designed to target uncovered paths.

### Test Case 1 – Standard Dataset

A dataset containing multiple rows and valid numeric values.

Purpose:

* Verify correct aggregation behavior.
* Execute the primary loop structure.

Coverage impact:

* Improves **line coverage** for aggregation logic.

---

### Test Case 2 – Dataset Containing Null Values

A dataset containing one or more `null` values.

Purpose:

* Trigger conditional checks such as:

```
if (n != null)
```

Coverage impact:

* Improves **branch coverage** by executing both true and false branches.

---

### Test Case 3 – Empty Dataset

An empty dataset was used.

Purpose:

* Verify correct loop termination behavior.

Coverage impact:

* Ensures loops behave correctly when no elements are present.

---

### Test Case 4 – Single Row Dataset

Dataset containing only one row.

Purpose:

* Evaluate boundary conditions for minimal datasets.

Coverage impact:

* Confirms loop behavior when the iteration count equals one.

---

### Test Case 5 – Multi-row Multi-column Dataset

Dataset containing several rows and columns.

Purpose:

* Exercise nested access operations across multiple elements.

Coverage impact:

* Increases overall structural coverage.

---

# 5 A detailed report of the coverage achieved of each class and method

Coverage analysis was performed using a coverage tool integrated into the development environment.

The following coverage metrics were obtained for the `DataUtilities` class:

| Method                   | Line Coverage |
| ------------------------ | ------------- |
| getCumulativePercentages | 83.3%         |
| createNumberArray        | 100%          |
| createNumberArray2D      | 100%          |
| calculateRowTotal        | 75%           |
| calculateColumnTotal     | 75%           |

Coverage visualization highlighted:

* **Green lines** – executed code
* **Red lines** – unexecuted code

Some red segments correspond to infeasible paths that cannot be executed due to logical constraints. 

---

# 6 Pros and Cons of coverage tools used and Metrics you report

Several coverage tools were evaluated during the lab.

Tools examined included:

* CodeCover
* Clover
* JaCoCo
* Coverlipse

However, none of these tools provided full support for **condition coverage**, which limited the ability to analyze complex conditional expressions. 

## Advantages of coverage tools

* Provide **quantitative measurement** of testing effectiveness
* Identify **untested code segments**
* Enable developers to focus testing efforts

## Limitations of coverage tools

* High coverage does not guarantee correctness
* Tools may not support advanced metrics
* Coverage can be misleading when infeasible paths exist

Because condition coverage was unavailable, **method coverage** was used as an alternative metric.

---

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation

Two major testing strategies are commonly used:

## Requirements-based testing

Requirements-based testing derives test cases directly from system requirements.

Advantages:

* Ensures the system satisfies functional specifications
* Focuses on user-visible behavior

Disadvantages:

* May fail to exercise internal logic paths
* Limited insight into code-level defects

---

## Coverage-based testing

Coverage-based testing derives test cases from the internal program structure.

Advantages:

* Ensures thorough exploration of control flow
* Identifies untested sections of code

Disadvantages:

* Does not guarantee functional correctness
* May generate tests unrelated to real-world scenarios

---

## Comparison

| Aspect       | Requirements-Based          | Coverage-Based                    |
| ------------ | --------------------------- | --------------------------------- |
| Focus        | Functional requirements     | Code structure                    |
| Primary goal | Validate system behavior    | Execute code paths                |
| Strength     | Validates user expectations | Identifies untested code          |
| Weakness     | May miss internal errors    | May ignore functional correctness |

Effective testing typically combines both approaches.

---

# 8 A discussion on how the team work/effort was divided and managed

The workload was divided among team members according to specific tasks.

Task allocation included:

* **Code analysis**

  Reviewing source code to identify infeasible paths and data-flow relationships.

* **Test design**

  Creating additional unit tests to improve coverage.

* **Coverage analysis**

  Running coverage tools and analyzing reports.

* **Documentation**

  Preparing the final lab report and summarizing results.

Regular communication ensured coordination between team members and consistent integration of results.

---

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Several challenges were encountered during the lab.

### Difficulties

* Some coverage tools lacked support for condition coverage.
* Identifying infeasible paths required careful inspection of loop conditions.
* Data-flow analysis required manual tracing of variable definitions and uses.

### Lessons learned

* Structural coverage metrics provide valuable insights into test adequacy.
* Data-flow testing reveals hidden dependencies between variables.
* Coverage results must always be interpreted carefully, especially when unreachable code exists.

---

# 10 Comments/feedback on the lab itself

This lab provided valuable hands-on experience with software testing and coverage analysis.

Key strengths of the lab include:

* Exposure to practical testing tools
* Experience designing test cases guided by coverage metrics
* Understanding limitations of coverage-based testing

Possible improvements include:

* Providing recommended coverage tools in advance
* Offering clearer examples of data-flow testing
* Including automated examples for condition coverage

Overall, the lab was effective in demonstrating how structural testing techniques contribute to improving software reliability.

---

If you want, I can also help you **add the missing high-scoring part professors look for in SENG 637**:

1️⃣ **Actual DU-pair coverage table per test case**
2️⃣ **Control Flow Graph explanation**
3️⃣ **Why the infeasible loops reduce achievable coverage**

These usually give **10–15% more marks** on this lab.
