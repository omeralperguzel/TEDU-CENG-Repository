// TO DO 1: Provides a 3x3 transformation matrix represented as an array containing 9 values arranged in column-major sequence.
// Initially, the transformation employs scaling, followed by rotation, and ultimately, translation.
// The specified rotation measurement is in degrees.
function GetTransform(positionX, positionY, rotation, scale) {
    //First, we need to calculate the cosine and sine of the rotation angle in radians.
    const cosA = Math.cos(rotation * (Math.PI / 180));
    const sinA = Math.sin(rotation * (Math.PI / 180));
  
    //Then, we have to calculate the individual elements of the transformation matrix for using them in a new matrix.
    const a = scale * cosA;  // This is needed for scaling in the X direction.
    const b = scale * -sinA; // This is needed for scaling in the Y direction with a negative sine component.
    const c = positionX;     // This is needed for translation in the X direction.
    const d = scale * sinA;  // This is needed for additional rotation component in the Y direction.
    const e = scale * cosA;  // This is needed for scaling in the Y direction.
    const f = positionY;     // This is needed for translation in the Y direction.

    //Now we define our matrix to getting the transformation in this drone. 
    const matrix = [a, d, 0, b, e, 0, c, f, 1];
    //Finally return the created matrix in this GetTransform function.
    return matrix;
}

// TO DO 2:Provides a 3x3 transformation matrix represented as an array containing 9 values arranged in column-major sequence.
// The inputs consist of transformation matrices following the identical format.
// The resulting transformation initially employs trans1 and subsequently applies trans2.
function ApplyTransform(trans1, trans2) {
  //It intializes an empty matrix named result.
  var result = [0, 0, 0, 0, 0, 0, 0, 0, 0,];

  // It combines two 3x3 transformation matrices (trans1 and trans2)
  for (row = 0; row < 3; row++) {
    for (col = 0; col < 3; col++) {
      for (i = 0; i < 3; i++) {
        // Now we have to multiply and accumulate elements from trans1 and trans2 for computing the result of this transformation.
        result[col + row * 3] += trans1[i + row * 3] * trans2[col + i * 3];
      }
    }
  }

  //Finally return the result of applied transformations.
  return result;

}