// BackGround is the background image to be changed.
// ForeGround is the foreground image.
// ForeGroundOpacity is the opacity of the foreground image.
// ForeGroundPosition is the The foreground image's location, measured in pixels. It can be negative, and the alignment of the foreground and background's top-left pixels is indicated by (0,0).

function composite(BackGround, ForeGround, ForeGroundOpacity, ForeGroundPosition) {
    var bgData = BackGround.data;
    var fgData = ForeGround.data;
    var width = BackGround.width;
    var height = BackGround.height;

    //Please write your code here according to comments

    // It loops through the rows and columns of the foreground image.
    for (var y = 0; y < ForeGround.height; y++) {
        for (var x = 0; x < ForeGround.width; x++) {
            // These variables calculates the corresponding pixel position in the background image.
            var bgX = ForeGroundPosition.x + x;
            var bgY = ForeGroundPosition.y + y;

            // This condition checks if the calculated pixel position is within the bounds of the background image or not.
            if (bgX >= 0 && bgX < width && bgY >= 0 && bgY < height) {
                // These variables calculates the index for the current pixel in the pixel data arrays.
                var bgIndex = (bgY * width + bgX) * 4; // Each pixel has RGBA values (4 values).
                var fgIndex = (y * ForeGround.width + x) * 4;

                // These background and foreground alpha values extracts the foreground and background pixel values.
                var fgAlpha = ForeGroundOpacity * fgData[fgIndex + 3] / 255; // It applies the provided opacity.
                var bgAlpha = 1 - fgAlpha; // It calculates the background alpha.

                // It is compositing the foreground and background pixels using alpha blending.
                bgData[bgIndex] = Math.round(fgData[fgIndex] * fgAlpha + bgData[bgIndex] * bgAlpha);
                bgData[bgIndex + 1] = Math.round(fgData[fgIndex + 1] * fgAlpha + bgData[bgIndex + 1] * bgAlpha);
                bgData[bgIndex + 2] = Math.round(fgData[fgIndex + 2] * fgAlpha + bgData[bgIndex + 2] * bgAlpha);
                bgData[bgIndex + 3] = Math.round((fgAlpha + bgAlpha) * 255); // It updates the alpha channel.
            }
        }
    }

}