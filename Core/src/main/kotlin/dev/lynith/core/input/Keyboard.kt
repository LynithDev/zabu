package dev.lynith.core.input

object Keyboard {

    val KEY_0 = Key(48);
    val KEY_1 = Key(49);
    val KEY_2 = Key(50);
    val KEY_3 = Key(51);
    val KEY_4 = Key(52);
    val KEY_5 = Key(53);
    val KEY_6 = Key(54);
    val KEY_7 = Key(55);
    val KEY_8 = Key(56);
    val KEY_9 = Key(57);
    val KEY_A = Key(65);
    val KEY_B = Key(66);
    val KEY_C = Key(67);
    val KEY_D = Key(68);
    val KEY_E = Key(69);
    val KEY_F = Key(70);
    val KEY_G = Key(71);
    val KEY_H = Key(72);
    val KEY_I = Key(73);
    val KEY_J = Key(74);
    val KEY_K = Key(75);
    val KEY_L = Key(76);
    val KEY_M = Key(77);
    val KEY_N = Key(78);
    val KEY_O = Key(79);
    val KEY_P = Key(80);
    val KEY_Q = Key(81);
    val KEY_R = Key(82);
    val KEY_S = Key(83);
    val KEY_T = Key(84);
    val KEY_U = Key(85);
    val KEY_V = Key(86);
    val KEY_W = Key(87);
    val KEY_X = Key(88);
    val KEY_Y = Key(89);
    val KEY_Z = Key(90);
    val KEY_F1 = Key(290);
    val KEY_F2 = Key(291);
    val KEY_F3 = Key(292);
    val KEY_F4 = Key(293);
    val KEY_F5 = Key(294);
    val KEY_F6 = Key(295);
    val KEY_F7 = Key(296);
    val KEY_F8 = Key(297);
    val KEY_F9 = Key(298);
    val KEY_F10 = Key(299);
    val KEY_F11 = Key(300);
    val KEY_F12 = Key(301);
    val KEY_F13 = Key(302);
    val KEY_F14 = Key(303);
    val KEY_F15 = Key(304);
    val KEY_F16 = Key(305);
    val KEY_F17 = Key(306);
    val KEY_F18 = Key(307);
    val KEY_F19 = Key(308);
    val KEY_F20 = Key(309);
    val KEY_F21 = Key(310);
    val KEY_F22 = Key(311);
    val KEY_F23 = Key(312);
    val KEY_F24 = Key(313);
    val KEY_F25 = Key(314);
    val KEY_NUMLOCK = Key(282);
    val KEY_NUMPAD0 = Key(320);
    val KEY_NUMPAD1 = Key(321);
    val KEY_NUMPAD2 = Key(322);
    val KEY_NUMPAD3 = Key(323);
    val KEY_NUMPAD4 = Key(324);
    val KEY_NUMPAD5 = Key(325);
    val KEY_NUMPAD6 = Key(326);
    val KEY_NUMPAD7 = Key(327);
    val KEY_NUMPAD8 = Key(328);
    val KEY_NUMPAD9 = Key(329);
    val KEY_NUMPADCOMMA = Key(330);
    val KEY_NUMPADENTER = Key(335);
    val KEY_NUMPADEQUALS = Key(336);
    val KEY_DOWN = Key(264);
    val KEY_LEFT = Key(263);
    val KEY_RIGHT = Key(262);
    val KEY_UP = Key(265);
    val KEY_ADD = Key(334);
    val KEY_APOSTROPHE = Key(39);
    val KEY_BACKSLASH = Key(92);
    val KEY_COMMA = Key(44);
    val KEY_EQUALS = Key(61);
    val KEY_GRAVE = Key(96);
    val KEY_LBRACKET = Key(91);
    val KEY_MINUS = Key(45);
    val KEY_MULTIPLY = Key(332);
    val KEY_PERIOD = Key(46);
    val KEY_RBRACKET = Key(93);
    val KEY_SEMICOLON = Key(59);
    val KEY_SLASH = Key(47);
    val KEY_SPACE = Key(32);
    val KEY_TAB = Key(258);
    val KEY_LALT = Key(342);
    val KEY_LCONTROL = Key(341);
    val KEY_LSHIFT = Key(340);
    val KEY_LWIN = Key(343);
    val KEY_RALT = Key(346);
    val KEY_RCONTROL = Key(345);
    val KEY_RSHIFT = Key(344);
    val KEY_RWIN = Key(347);
    val KEY_RETURN = Key(257);
    val KEY_ESCAPE = Key(256);
    val KEY_BACKSPACE = Key(259);
    val KEY_DELETE = Key(261);
    val KEY_END = Key(269);
    val KEY_HOME = Key(268);
    val KEY_INSERT = Key(260);
    val KEY_PAGEDOWN = Key(267);
    val KEY_PAGEUP = Key(266);
    val KEY_CAPSLOCK = Key(280);
    val KEY_PAUSE = Key(284);
    val KEY_SCROLLLOCK = Key(281);
    val KEY_PRINTSCREEN = Key(283);

    /**
     * Translates a legacy LWJGL 2 keycode to a modern GLFW keycode.
     * @param legacyKeyCode The keycode from LWJGL 2.
     * @return The keycode for GLFW.
     */
    fun translateToModern(legacyKeyCode: Int): Key {
        if (legacyKeyCode < 0 || legacyKeyCode >= LWJGL2_TO_GLFW.size)
            return Key(-1);

        return Key(LWJGL2_TO_GLFW[legacyKeyCode]);
    }

    private val LWJGL2_TO_GLFW = IntArray(84)

    init {
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