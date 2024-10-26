package alatoo.smart_finance.entity;

public enum ExpensesCategory {
    SHOPPING("Shopping"),
    FOODS("Foods"),
    BILLS("Bills"),
    ENTERTAINMENT("Entertainment"),
    TRANSPORTATION("Transportation"),
    HEALTHCARE("Healthcare"),
    OTHER("Other");

    private final String displayName;

    ExpensesCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
