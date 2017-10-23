package mk.ukim.finki.lab2;

class PhoneContact extends Contact {
    private String phone;

    private enum Operartor {
        VIP, ONE, TMOBILE
    }

    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public Operartor getOperator() {
        String operator = phone.substring(0, 3);
        if (operator.equals("070") || operator.equals("071") || operator.equals("072"))
            return Operartor.values()[2];
        if (operator.equals("075") || operator.equals("076"))
            return Operartor.values()[1];
        if (operator.equals("077") || operator.equals("078"))
            return Operartor.values()[0];
        return null;
    }

    @Override
    public String getType() {
        return "Phone";
    }
}
