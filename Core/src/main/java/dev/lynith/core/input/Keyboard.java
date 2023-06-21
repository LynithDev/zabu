package dev.lynith.core.input;

public class Keyboard {

    public static final Key KEY_0 = new Key(48);
    public static final Key KEY_1 = new Key(49);
    public static final Key KEY_2 = new Key(50);
    public static final Key KEY_3 = new Key(51);
    public static final Key KEY_4 = new Key(52);
    public static final Key KEY_5 = new Key(53);
    public static final Key KEY_6 = new Key(54);
    public static final Key KEY_7 = new Key(55);
    public static final Key KEY_8 = new Key(56);
    public static final Key KEY_9 = new Key(57);
    public static final Key KEY_A = new Key(65);
    public static final Key KEY_B = new Key(66);
    public static final Key KEY_C = new Key(67);
    public static final Key KEY_D = new Key(68);
    public static final Key KEY_E = new Key(69);
    public static final Key KEY_F = new Key(70);
    public static final Key KEY_G = new Key(71);
    public static final Key KEY_H = new Key(72);
    public static final Key KEY_I = new Key(73);
    public static final Key KEY_J = new Key(74);
    public static final Key KEY_K = new Key(75);
    public static final Key KEY_L = new Key(76);
    public static final Key KEY_M = new Key(77);
    public static final Key KEY_N = new Key(78);
    public static final Key KEY_O = new Key(79);
    public static final Key KEY_P = new Key(80);
    public static final Key KEY_Q = new Key(81);
    public static final Key KEY_R = new Key(82);
    public static final Key KEY_S = new Key(83);
    public static final Key KEY_T = new Key(84);
    public static final Key KEY_U = new Key(85);
    public static final Key KEY_V = new Key(86);
    public static final Key KEY_W = new Key(87);
    public static final Key KEY_X = new Key(88);
    public static final Key KEY_Y = new Key(89);
    public static final Key KEY_Z = new Key(90);
    public static final Key KEY_F1 = new Key(290);
    public static final Key KEY_F2 = new Key(291);
    public static final Key KEY_F3 = new Key(292);
    public static final Key KEY_F4 = new Key(293);
    public static final Key KEY_F5 = new Key(294);
    public static final Key KEY_F6 = new Key(295);
    public static final Key KEY_F7 = new Key(296);
    public static final Key KEY_F8 = new Key(297);
    public static final Key KEY_F9 = new Key(298);
    public static final Key KEY_F10 = new Key(299);
    public static final Key KEY_F11 = new Key(300);
    public static final Key KEY_F12 = new Key(301);
    public static final Key KEY_F13 = new Key(302);
    public static final Key KEY_F14 = new Key(303);
    public static final Key KEY_F15 = new Key(304);
    public static final Key KEY_F16 = new Key(305);
    public static final Key KEY_F17 = new Key(306);
    public static final Key KEY_F18 = new Key(307);
    public static final Key KEY_F19 = new Key(308);
    public static final Key KEY_F20 = new Key(309);
    public static final Key KEY_F21 = new Key(310);
    public static final Key KEY_F22 = new Key(311);
    public static final Key KEY_F23 = new Key(312);
    public static final Key KEY_F24 = new Key(313);
    public static final Key KEY_F25 = new Key(314);
    public static final Key KEY_NUMLOCK = new Key(282);
    public static final Key KEY_NUMPAD0 = new Key(320);
    public static final Key KEY_NUMPAD1 = new Key(321);
    public static final Key KEY_NUMPAD2 = new Key(322);
    public static final Key KEY_NUMPAD3 = new Key(323);
    public static final Key KEY_NUMPAD4 = new Key(324);
    public static final Key KEY_NUMPAD5 = new Key(325);
    public static final Key KEY_NUMPAD6 = new Key(326);
    public static final Key KEY_NUMPAD7 = new Key(327);
    public static final Key KEY_NUMPAD8 = new Key(328);
    public static final Key KEY_NUMPAD9 = new Key(329);
    public static final Key KEY_NUMPADCOMMA = new Key(330);
    public static final Key KEY_NUMPADENTER = new Key(335);
    public static final Key KEY_NUMPADEQUALS = new Key(336);
    public static final Key KEY_DOWN = new Key(264);
    public static final Key KEY_LEFT = new Key(263);
    public static final Key KEY_RIGHT = new Key(262);
    public static final Key KEY_UP = new Key(265);
    public static final Key KEY_ADD = new Key(334);
    public static final Key KEY_APOSTROPHE = new Key(39);
    public static final Key KEY_BACKSLASH = new Key(92);
    public static final Key KEY_COMMA = new Key(44);
    public static final Key KEY_EQUALS = new Key(61);
    public static final Key KEY_GRAVE = new Key(96);
    public static final Key KEY_LBRACKET = new Key(91);
    public static final Key KEY_MINUS = new Key(45);
    public static final Key KEY_MULTIPLY = new Key(332);
    public static final Key KEY_PERIOD = new Key(46);
    public static final Key KEY_RBRACKET = new Key(93);
    public static final Key KEY_SEMICOLON = new Key(59);
    public static final Key KEY_SLASH = new Key(47);
    public static final Key KEY_SPACE = new Key(32);
    public static final Key KEY_TAB = new Key(258);
    public static final Key KEY_LALT = new Key(342);
    public static final Key KEY_LCONTROL = new Key(341);
    public static final Key KEY_LSHIFT = new Key(340);
    public static final Key KEY_LWIN = new Key(343);
    public static final Key KEY_RALT = new Key(346);
    public static final Key KEY_RCONTROL = new Key(345);
    public static final Key KEY_RSHIFT = new Key(344);
    public static final Key KEY_RWIN = new Key(347);
    public static final Key KEY_RETURN = new Key(257);
    public static final Key KEY_ESCAPE = new Key(256);
    public static final Key KEY_BACKSPACE = new Key(259);
    public static final Key KEY_DELETE = new Key(261);
    public static final Key KEY_END = new Key(269);
    public static final Key KEY_HOME = new Key(268);
    public static final Key KEY_INSERT = new Key(260);
    public static final Key KEY_PAGEDOWN = new Key(267);
    public static final Key KEY_PAGEUP = new Key(266);
    public static final Key KEY_CAPSLOCK = new Key(280);
    public static final Key KEY_PAUSE = new Key(284);
    public static final Key KEY_SCROLLLOCK = new Key(281);
    public static final Key KEY_PRINTSCREEN = new Key(283);

    /**
     * Translates a legacy LWJGL 2 keycode to a modern GLFW keycode.
     * @param legacyKeyCode The keycode from LWJGL 2.
     * @return The keycode for GLFW.
     */
    public static Key translateToModern(int legacyKeyCode) {
        if (legacyKeyCode < 0 || legacyKeyCode >= LWJGL2_TO_GLFW.length)
            return new Key(-1);

        return new Key(LWJGL2_TO_GLFW[legacyKeyCode]);
    }

    private static final int[] LWJGL2_TO_GLFW = new int[256];

    static {
        /* LEGACY, MODERN */
        LWJGL2_TO_GLFW[0] = -1;                 // KEY_NONE
        LWJGL2_TO_GLFW[1] = 256;                // KEY_ESCAPE
        LWJGL2_TO_GLFW[2] = 49;                 // KEY_1
        LWJGL2_TO_GLFW[3] = 50;                 // KEY_2
        LWJGL2_TO_GLFW[4] = 51;                 // KEY_3
        LWJGL2_TO_GLFW[5] = 52;                 // KEY_4
        LWJGL2_TO_GLFW[6] = 53;                 // KEY_5
        LWJGL2_TO_GLFW[7] = 54;                 // KEY_6
        LWJGL2_TO_GLFW[8] = 55;                 // KEY_7
        LWJGL2_TO_GLFW[9] = 56;                 // KEY_8
        LWJGL2_TO_GLFW[10] = 57;                 // KEY_9
        LWJGL2_TO_GLFW[11] = 48;                 // KEY_0
        LWJGL2_TO_GLFW[12] = 45;                 // KEY_MINUS
        LWJGL2_TO_GLFW[13] = 61;                 // KEY_EQUALS
        LWJGL2_TO_GLFW[14] = 259;                // KEY_BACK
        LWJGL2_TO_GLFW[15] = 258;                // KEY_TAB
        LWJGL2_TO_GLFW[16] = 81;                 // KEY_Q
        LWJGL2_TO_GLFW[17] = 87;                 // KEY_W
        LWJGL2_TO_GLFW[18] = 69;                 // KEY_E
        LWJGL2_TO_GLFW[19] = 82;                 // KEY_R
        LWJGL2_TO_GLFW[20] = 84;                 // KEY_T
        LWJGL2_TO_GLFW[21] = 89;                 // KEY_Y
        LWJGL2_TO_GLFW[22] = 85;                 // KEY_U
        LWJGL2_TO_GLFW[23] = 73;                 // KEY_I
        LWJGL2_TO_GLFW[24] = 79;                 // KEY_O
        LWJGL2_TO_GLFW[25] = 80;                 // KEY_P
        LWJGL2_TO_GLFW[26] = 91;                 // KEY_LBRACKET
        LWJGL2_TO_GLFW[27] = 93;                 // KEY_RBRACKET
        LWJGL2_TO_GLFW[28] = 257;                // KEY_RETURN
        LWJGL2_TO_GLFW[29] = 341;                // KEY_LCONTROL
        LWJGL2_TO_GLFW[30] = 65;                 // KEY_A
        LWJGL2_TO_GLFW[31] = 83;                 // KEY_S
        LWJGL2_TO_GLFW[32] = 68;                 // KEY_D
        LWJGL2_TO_GLFW[33] = 70;                 // KEY_F
        LWJGL2_TO_GLFW[34] = 71;                 // KEY_G
        LWJGL2_TO_GLFW[35] = 72;                 // KEY_H
        LWJGL2_TO_GLFW[36] = 74;                 // KEY_J
        LWJGL2_TO_GLFW[37] = 75;                 // KEY_K
        LWJGL2_TO_GLFW[38] = 76;                 // KEY_L
        LWJGL2_TO_GLFW[39] = 59;                 // KEY_SEMICOLON
        LWJGL2_TO_GLFW[40] = 39;                 // KEY_APOSTROPHE
        LWJGL2_TO_GLFW[41] = 96;                 // KEY_GRAVE
        LWJGL2_TO_GLFW[42] = 340;                // KEY_LSHIFT
        LWJGL2_TO_GLFW[43] = 92;                 // KEY_BACKSLASH
        LWJGL2_TO_GLFW[44] = 90;                 // KEY_Z
        LWJGL2_TO_GLFW[45] = 88;                 // KEY_X
        LWJGL2_TO_GLFW[46] = 67;                 // KEY_C
        LWJGL2_TO_GLFW[47] = 86;                 // KEY_V
        LWJGL2_TO_GLFW[48] = 66;                 // KEY_B
        LWJGL2_TO_GLFW[49] = 78;                 // KEY_N
        LWJGL2_TO_GLFW[50] = 77;                 // KEY_M
        LWJGL2_TO_GLFW[51] = 44;                 // KEY_COMMA
        LWJGL2_TO_GLFW[52] = 46;                 // KEY_PERIOD
        LWJGL2_TO_GLFW[53] = 47;                 // KEY_SLASH
        LWJGL2_TO_GLFW[54] = 344;                // KEY_RSHIFT
        LWJGL2_TO_GLFW[55] = 331;                // KEY_MULTIPLY
        LWJGL2_TO_GLFW[56] = 340;                // KEY_LMENU
        LWJGL2_TO_GLFW[57] = 32;                 // KEY_SPACE
        LWJGL2_TO_GLFW[58] = 346;                // KEY_CAPITAL
        LWJGL2_TO_GLFW[59] = 291;                // KEY_F1
        LWJGL2_TO_GLFW[60] = 292;                // KEY_F2
        LWJGL2_TO_GLFW[61] = 293;                // KEY_F3
        LWJGL2_TO_GLFW[62] = 294;                // KEY_F4
        LWJGL2_TO_GLFW[63] = 295;                // KEY_F5
        LWJGL2_TO_GLFW[64] = 296;                // KEY_F6
        LWJGL2_TO_GLFW[65] = 297;                // KEY_F7
        LWJGL2_TO_GLFW[66] = 298;                // KEY_F8
        LWJGL2_TO_GLFW[67] = 299;                // KEY_F9
        LWJGL2_TO_GLFW[68] = 300;                // KEY_F10
        LWJGL2_TO_GLFW[69] = 301;                // KEY_NUMLOCK
        LWJGL2_TO_GLFW[70] = 302;                // KEY_SCROLL
        LWJGL2_TO_GLFW[71] = 303;                // KEY_NUMPAD7
        LWJGL2_TO_GLFW[72] = 304;                // KEY_NUMPAD8
        LWJGL2_TO_GLFW[73] = 305;                // KEY_NUMPAD9
        LWJGL2_TO_GLFW[74] = 327;                // KEY_SUBTRACT
        LWJGL2_TO_GLFW[75] = 307;                // KEY_NUMPAD4
        LWJGL2_TO_GLFW[76] = 308;                // KEY_NUMPAD5
        LWJGL2_TO_GLFW[77] = 309;                // KEY_NUMPAD6
        LWJGL2_TO_GLFW[78] = 333;                // KEY_ADD
        LWJGL2_TO_GLFW[79] = 311;                // KEY_NUMPAD1
        LWJGL2_TO_GLFW[80] = 312;                // KEY_NUMPAD2
        LWJGL2_TO_GLFW[81] = 313;                // KEY_NUMPAD3
        LWJGL2_TO_GLFW[82] = 330;                // KEY_NUMPAD0
        LWJGL2_TO_GLFW[83] = 314;              // KEY_DECIMAL
    }

}
