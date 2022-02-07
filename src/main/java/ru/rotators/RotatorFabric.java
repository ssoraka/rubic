package ru.rotators;

import ru.Cube;
import ru.Rubic;

public abstract class RotatorFabric {
    public static Rotator createUpRotator(Cube[][][] cube) {
        return new Rotator((i, j) -> cube[2][i][j],
                (i, j, c) -> cube[2][i][j] = c,
                Cube::rotUp,
                Cube::rotUpRev);
    }

    public static Rotator createDownRotator(Cube[][][] cube) {
        return new Rotator((i, j) -> cube[0][i][j],
                (i, j, c) -> cube[0][i][j] = c,
                Cube::rotUp,
                Cube::rotUpRev);
    }

    public static Rotator createFrontRotator(Cube[][][] cube) {
        return new Rotator((i, j) -> cube[i][0][j],
                (i, j, c) -> cube[i][0][j] = c,
                Cube::rotFront,
                Cube::rotFrontRev);
    }

    public static Rotator createBackRotator(Cube[][][] cube) {
        return new Rotator((i, j) -> cube[i][2][j],
                (i, j, c) -> cube[i][2][j] = c,
                Cube::rotFront,
                Cube::rotFrontRev);
    }

    public static Rotator createRightRotator(Cube[][][] cube) {
        return new Rotator((i, j) -> cube[i][j][0],
                (i, j, c) -> cube[i][j][0] = c,
                Cube::rotRight,
                Cube::rotRightRev);
    }

    public static Rotator createLeftRotator(Cube[][][] cube) {
        return new Rotator((i, j) -> cube[i][j][2],
                (i, j, c) -> cube[i][j][2] = c,
                Cube::rotRight,
                Cube::rotRightRev);
    }
}
