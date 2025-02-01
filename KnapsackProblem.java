import java.util.ArrayList;
import java.util.List;

class KnapsackItem {
    int weight;
    int value;

    KnapsackItem(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class KnapsackProblem {
    public static void main(String[] args) {
        List<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem(12, 4));
        items.add(new KnapsackItem(1, 2));
        items.add(new KnapsackItem(4, 10));
        items.add(new KnapsackItem(2, 2));
        items.add(new KnapsackItem(1, 1));
        int maxWeight = 15;

        List<KnapsackItem> selectedItems = solveKnapsack(items, maxWeight);
        System.out.println("Selected items for maximum value:");
        for (KnapsackItem item : selectedItems) {
            System.out.println("Weight: " + item.weight + ", Value: " + item.value);
        }
    }

    public static List<KnapsackItem> solveKnapsack(List<KnapsackItem> items, int maxWeight) {
        List<KnapsackItem> selectedItems = new ArrayList<>();
        backtrack(items, 0, 0, 0, maxWeight, selectedItems, new ArrayList<>());
        return selectedItems;
    }

    public static void backtrack(List<KnapsackItem> items, int index, int currentWeight, int currentValue,
                                 int maxWeight, List<KnapsackItem> selectedItems, List<KnapsackItem> currentSelection) {
        if (currentWeight > maxWeight) {
            return;
        }

        if (index == items.size()) {
            if (currentValue > calculateTotalValue(selectedItems)) {
                selectedItems.clear();
                selectedItems.addAll(currentSelection);
            }
            return;
        }

        KnapsackItem currentItem = items.get(index);

        currentSelection.add(currentItem);
        backtrack(items, index + 1, currentWeight + currentItem.weight, currentValue + currentItem.value,
                maxWeight, selectedItems, currentSelection);
        currentSelection.remove(currentItem);
        backtrack(items, index + 1, currentWeight, currentValue, maxWeight, selectedItems, currentSelection);
    }

    public static int calculateTotalValue(List<KnapsackItem> items) {
        int totalValue = 0;
        for (KnapsackItem item : items) {
            totalValue += item.value;
        }
        return totalValue;
    }
}
