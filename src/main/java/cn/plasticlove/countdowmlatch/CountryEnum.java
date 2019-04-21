package cn.plasticlove.countdowmlatch;

/**
 * The enum Country enum.
 */
public enum CountryEnum {


    /**
     * Qi country enum.
     */
    QI(0,"齐"),
    /**
     * Chu country enum.
     */
    CHU(1,"楚"),
    /**
     * Yan country enum.
     */
    YAN(2,"燕"),
    /**
     * Han country enum.
     */
    HAN(3,"韩"),
    /**
     * Zhao country enum.
     */
    ZHAO(4,"赵"),
    /**
     * Wei country enum.
     */
    WEI(5,"魏");

    private int code;
    private String msg;

    CountryEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Foreach enum country enum.
     *
     * @param index the index
     * @return the country enum
     */
    public static CountryEnum foreachEnum(int index){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum elem:countryEnums){
            if (elem.getCode()==index){
                return elem;
            }
        }
        return null;
    }



}
