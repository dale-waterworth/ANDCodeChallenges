package challenging.chains;

class Cell {
    public String value;
    public int x;
    public int y;
    public boolean aboveLink = false;
    public boolean rightLink = false;
    public boolean belowLink = false;
    public boolean leftLink = false;
    public boolean ignore;
    public boolean isChain;
    public boolean isPartOfChain;
    public int linkCount;

    public Cell(String value) {
        this.value = value;
        int ascii = value.toCharArray()[0];
        isChain = ascii == 35;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value='" + value + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", aboveLink=" + aboveLink +
                ", rightLink=" + rightLink +
                ", belowLink=" + belowLink +
                ", leftLink=" + leftLink +
                ", ignore=" + ignore +
                ", isChain=" + isChain +
                ", isPartOfChain=" + isPartOfChain +
                ", linkCount=" + linkCount +
                '}';
    }
}
