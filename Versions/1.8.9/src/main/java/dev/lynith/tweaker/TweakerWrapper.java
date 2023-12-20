package dev.lynith.tweaker;

import net.minecraft.launchwrapper.Launch;

import java.util.Arrays;

public class TweakerWrapper {

    public static void init(String[] strings) {
        boolean isModificationApplied = System.getProperty("dev.lynith.internal.isModificationApplied", "false").equals("true");

        if (!isModificationApplied) {
            System.setProperty("dev.lynith.internal.isModificationApplied", "true");

            String[] args = new String[] { "--tweakClass", Tweaker.class.getName() };
            String[] concat = Arrays.copyOf(strings, strings.length + args.length);
            System.arraycopy(args, 0, concat, strings.length, args.length);

            Launch.main(concat);
        }
    }

}
