package StrategyV2;

public class PhysicalPerson implements StrategyMembers {

    private final String PF = "PF";

    @Override
    public void getAccountInfo() {
        System.out.println("Obtain business member info....");
    }

    @Override
    public boolean support(String member) {
        return PF.equals(member);
    }

    public PhysicalPerson() {
    }
}
