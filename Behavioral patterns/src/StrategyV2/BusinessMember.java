package StrategyV2;

public class BusinessMember implements StrategyMembers{

    private final String BUSINESS = "BUSINESS";

    @Override
    public void getAccountInfo() {
        System.out.println("Obtain business member info....");
    }

    @Override
    public boolean support(String member) {
        return BUSINESS.equals(member);
    }

    public BusinessMember() {
    }
}
