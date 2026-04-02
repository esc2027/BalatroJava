public class Image {
    public static void draw(int[][] arr) {
        StringBuilder image = new StringBuilder();

        for (int x = 0; x < arr.length; x += 2) { // step 2 rows at a time
            int[] topRow = arr[x];
            int[] bottomRow = (x + 1 < arr.length) ? arr[x + 1] : topRow;

            image.append("    ");

            for (int y = 0; y < topRow.length; y++) {
                int top = topRow[y];
                int bottom = bottomRow[y];

                if (top == bottom) {
                    image.append(colorPickerBG(top))
                            .append(' ')
                            .append(Color.RESETCOLOR);
                } else if(bottom == 0) {
                    image.append(colorPickerBG(bottom))
                            .append(colorPicker(top))
                            .append('▀')
                            .append(Color.RESETCOLOR);
                } else {
                    image.append(colorPickerBG(top))
                            .append(colorPicker(bottom))
                            .append('▄')
                            .append(Color.RESETCOLOR);
                }
            }
            image.append("\n");
        }

        System.out.print(image);
    }

    public static String colorPicker(int color) {
        return switch(color) {
            case 0 -> "\u001b[38;2;25;26;28m";
            case 1 -> "\u001b[38;2;31;54;59m";
            case 2 -> "\u001b[38;2;67;3;1m";
            case 3 -> "\u001b[38;2;135;2;0m";
            case 4 -> "\u001b[38;2;243;5;0m";
            case 5 -> "\u001b[38;2;34;60;88m";
            case 6 -> "\u001b[38;2;2;105;171m";
            case 7 -> "\u001b[38;2;144;190;230m";
            case 8 -> "\u001b[38;2;248;245;240m";
            default -> "?";
        };
    }
    public static String colorPickerBG(int color) {
        return switch(color) {
            case 0 -> Color.RESETCOLOR;
            case 1 -> "\u001b[48;2;31;54;59m";
            case 2 -> "\u001b[48;2;67;3;1m";
            case 3 -> "\u001b[48;2;135;2;0m";
            case 4 -> "\u001b[48;2;243;5;0m";
            case 5 -> "\u001b[48;2;34;60;88m";
            case 6 -> "\u001b[48;2;2;105;171m";
            case 7 -> "\u001b[48;2;144;190;230m";
            case 8 -> "\u001b[48;2;248;245;240m";
            default -> "?";
        };
    }
}
