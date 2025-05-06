package com.ytech.common;

import java.awt.Color;

public class AppTheme {
        public Color primaryColor;
        public Color onPrimaryColor;
        public Color secondaryColor;
        public Color onSecondaryColor;
        public Color tertiaryColor;
        public Color onTertiaryColor;
        public Color surfaceColor;
        public Color onSurfaceColor;
        public Color cardColor;
        public Color onCardColor;
        public static AppTheme lightTheme = new AppTheme(new Color(30, 144, 255), Color.BLACK, Color.ORANGE, Color.BLUE,
                        new Color(30, 60, 30),
                        new Color(30, 80, 30), Color.WHITE, new Color(200, 200, 255), new Color(40, 40, 90),
                        Color.PINK);
        public static AppTheme darkTheme = new AppTheme(Color.BLUE, Color.BLACK, Color.ORANGE, Color.BLUE, Color.PINK,
                        Color.BLACK, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE);

        public static AppTheme theme = lightTheme;

        AppTheme(Color primary, Color onPrimary, Color secondary, Color onSecondary, Color tertiary, Color onTertiary,
                        Color surface, Color onSurface, Color card, Color onCard) {
                primaryColor = primary;
                secondaryColor = secondary;
                tertiaryColor = tertiary;

                onPrimaryColor = onPrimary;
                onSecondaryColor = onSecondary;
                onTertiaryColor = onTertiary;

                surfaceColor = surface;
                onSurfaceColor = onSurface;

                cardColor = card;
                onCardColor = onCard;
        }

}
