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

Software reliability depends heavily on the quality and adequacy of the testing process. Unit testing is one of the most widely used techniques for verifying the correctness of individual components in a software system. In addition to validating functional behavior, modern testing practices emphasize evaluating the adequacy of a test suite using structural analysis techniques. White-box testing provides a systematic way to achieve this by analyzing the internal structure of the source code and ensuring that test cases exercise critical execution paths.

This assignment focuses on evaluating and improving the adequacy of a unit test suite using code coverage criteria. Code coverage metrics provide quantitative indicators of how thoroughly a test suite exercises the system under test (SUT). Common structural coverage criteria include statement coverage, branch (decision) coverage, and condition coverage, each representing a progressively stronger level of testing completeness. By measuring these metrics, testers can identify untested regions of code and design additional test cases to improve the overall quality of the test suite.

The system under test in this assignment is JFreeChart, an open-source Java framework used for creating charts and data visualizations. JFreeChart is designed to be integrated into other applications as a reusable component, making the correctness and robustness of its API functions critical. Two classes from the org.jfree.data package—DataUtilities and Range—are selected for detailed analysis and testing in this assignment.

To perform the testing and coverage analysis, the JUnit testing framework is used to execute unit tests within the Eclipse development environment, while code coverage tools are used to measure structural coverage metrics. Coverage tools help identify the portions of code that are executed by the existing test suite and reveal areas that require additional test cases to achieve higher coverage.

In addition to automated coverage measurement, this assignment also introduces data-flow testing, which examines how variables are defined and subsequently used during program execution. Data-flow coverage focuses on identifying definition-use (DU) pairs, ensuring that variable definitions propagate correctly to their uses along feasible execution paths. This analysis provides deeper insight into potential faults that may not be detected through control-flow coverage alone.

The objectives of this assignment are therefore threefold: to measure the adequacy of an existing test suite using coverage tools, to design additional unit tests that increase structural coverage, and to manually analyze data-flow coverage for selected methods. Through these activities, the assignment demonstrates how coverage-based testing techniques can be used to systematically improve test effectiveness while highlighting both the strengths and limitations of coverage metrics as indicators of software quality.

If you want, I can also give you a slightly shorter version (≈180 words) that professors sometimes prefer because long introductions lose marks in software engineering reports.
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
<img width="718" height="479" alt="spisex" src="https://github.com/user-attachments/assets/3424c4b9-aec1-4e5a-82a1-faed7bebb3cc" />

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
