package OOPProject;

public interface HeliBlueprint {
    void flyDiagonallyEastward();
    void flyDiagonallyWestward();
    void buildHelicopter(boolean flipped) throws HelicopterBuildFailed;
}
