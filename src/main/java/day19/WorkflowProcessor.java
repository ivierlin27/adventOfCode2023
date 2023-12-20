package main.java.day19;

import main.java.day19.domain.Part;
import main.java.day19.domain.PartRange;
import main.java.day19.domain.WorkItem;
import main.java.day19.domain.Workflow;
import main.java.day19.domain.WorkflowItem;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WorkflowProcessor {

    public static final String STARTING_WORKFLOW_NAME = "in";
    public static final String ACCEPTED = "A";
    public static final String REJECTED = "R";
    public static final char GREATER = '>';
    public static final char LESS = '<';
    public static final String OPEN_CURLY = "{";
    public static final String CLOSE_CURLY = "}";
    public static final String COMMA = ",";
    public static final String EMPTY_STRING = "";
    Map<String, Workflow> workflows = new HashMap<>();
    List<Part> parts = new ArrayList<>();
    boolean workflowsLoaded = false;
    public long readInputLine(Input input) {

        if (input.getCurrentLine().isEmpty()) {
            workflowsLoaded = true;
            return 0;
        }

        if (!workflowsLoaded) {
            String[] inputParts = input.getCurrentLine().split("\\{");
            String workflowName = inputParts[0];
            String[] itemsParts = inputParts[1].replace(CLOSE_CURLY, EMPTY_STRING).split(COMMA);
            workflows.put(workflowName, new Workflow(workflowName, getWorkflowItems(itemsParts), itemsParts[itemsParts.length - 1]));
        } else {
            parts.add(new Part(getRecords(input.getCurrentLine())));
        }
        return 0;
    }

    public long processParts() {
        Workflow workFlow = workflows.get(STARTING_WORKFLOW_NAME);
        return parts.stream().map(part -> evaluatePart(part, workFlow)).reduce(0L, Long::sum);
    }

    public long findAcceptedCombinations() {
        int min = 1;
        int max = 4001;
        LinkedList<WorkItem> queue = new LinkedList<>();
        queue.add(new WorkItem(STARTING_WORKFLOW_NAME,
                new PartRange(new long[]{min, min, min, min}, new long[]{max, max, max, max})));
        long sum = 0;
        while (!queue.isEmpty()) {
            WorkItem workItem = queue.remove();
            if (workItem.label().equals(ACCEPTED)) {
                sum += workItem.partRange().combinations();
            } else if (workItem.label().equals(REJECTED)) {
                // do nothing!
            } else {
                processWorkflow(workflows.get(workItem.label()), workItem.partRange(), queue);
            }
        }
        return sum;
    }

    private void processWorkflow(Workflow workflow, PartRange partRange, LinkedList<WorkItem> queue) {
        PartRange open = partRange;
        for (WorkflowItem workflowItem : workflow.workflowItems()) {
            if (open == null) {
                return;
            }
            open = processWorkflowItem(workflowItem, open, queue);
        }
        if (open != null) {
            queue.add(new WorkItem(workflow.elseClause(), open));
        }
    }

    private PartRange processWorkflowItem(WorkflowItem workflowItem, PartRange partRange, LinkedList<WorkItem> queue) {
        int index = workflowItem.indexFromCharacter();
        if (workflowItem.operation() == LESS) {
            if (partRange.min()[index] < workflowItem.num() && partRange.max()[index] > workflowItem.num()) {
                long[] min = Arrays.copyOf(partRange.min(), 4);
                long[] max = Arrays.copyOf(partRange.max(), 4);
                max[index] = workflowItem.num();
                queue.add(new WorkItem(workflowItem.result(), new PartRange(partRange.min(), max)));
                min[index] = workflowItem.num();
                return new PartRange(min, partRange.max());
            } else if (partRange.max()[index] <= workflowItem.num()) {
                queue.add(new WorkItem(workflowItem.result(), partRange));
                return null;
            } else {
                return partRange;
            }
        } else {
            if (partRange.min()[index] < workflowItem.num() && partRange.max()[index] > workflowItem.num() + 1) {
                long[] min = Arrays.copyOf(partRange.min(), 4);
                long[] max = Arrays.copyOf(partRange.max(), 4);
                min[index] = workflowItem.num() + 1;
                queue.add(new WorkItem(workflowItem.result(), new PartRange(min, partRange.max())));
                max[index] = workflowItem.num() + 1;
                return new PartRange(partRange.min(), max);
            } else if (partRange.min()[index] > workflowItem.num()) {
                queue.add(new WorkItem(workflowItem.result(), partRange));
                return null;
            } else {
                return partRange;
            }
        }
    }

    private long evaluatePart(Part part, Workflow workflow) {
        List<WorkflowItem> workflowItems = workflow.workflowItems();
        for (WorkflowItem workflowItem : workflowItems) {
            Long value = part.records().get(workflowItem.character());
            if (evaluate(value, workflowItem.operation(), workflowItem.num())) {
                String result = workflowItem.result();
                return evaluateResult(part, result);
            }
        }
        return evaluateResult(part, workflow.elseClause());
    }

    private long evaluateResult(Part part, String result) {
        if (result.equals(ACCEPTED)) {
            return computeValue(part);
        } else if (result.equals(REJECTED)) {
            return 0;
        } else {
            return evaluatePart(part, workflows.get(result));
        }
    }

    private static long computeValue(Part part) {
        return part.records().values().stream().reduce(0L, Long::sum);
    }

    private static boolean evaluate(long num1, char operation, long num2) {
        return switch (operation) {
            case GREATER -> num1 > num2;
            case LESS -> num1 < num2;
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }

    private static Map<Character, Long> getRecords(String currentLine) {
        String[] inputParts = currentLine.replace(OPEN_CURLY, EMPTY_STRING).replace(CLOSE_CURLY, EMPTY_STRING).split(COMMA);
        Map<Character, Long> records = new HashMap<>();
        for (String part : inputParts) {
            records.put(part.charAt(0), Long.parseLong(part.substring(2)));
        }
        return records;
    }

    private static List<WorkflowItem> getWorkflowItems(String[] itemParts) {
        List<WorkflowItem> workflowItems = new ArrayList<>();
        for (int i = 0; i < itemParts.length - 1; i++) {
            char character = itemParts[i].charAt(0);
            char operation = itemParts[i].charAt(1);
            long num = Long.parseLong(itemParts[i].substring(2, itemParts[i].indexOf(':')));
            String result = itemParts[i].substring(itemParts[i].indexOf(':') + 1);
            workflowItems.add(new WorkflowItem(character, operation, num, result));
        }

        return workflowItems;
    }
}
