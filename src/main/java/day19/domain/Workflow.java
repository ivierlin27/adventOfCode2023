package main.java.day19.domain;

import java.util.List;

public record Workflow(String name, List<WorkflowItem> workflowItems, String elseClause) {
}
