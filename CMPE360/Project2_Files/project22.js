function GetTransform(positionX, positionY, rotation, scale){
    const rad_rotation = -(rotation * Math.PI/180);
    const cos = Math.cos(rad_rotation), sin = Math.sin(rad_rotation);

    const m = [
        [scale * cos, scale * sin, positionX], [-sin*scale, scale*cos, positionY], [0, 0, 1]
    ];

    const matrix = [m[0][0], m[1][0], m[2][0], m[0][1], m[1][1], m[2][1], m[0][2], m[1][2], m[2][2]];

    return matrix;
}

function ApplyTransform(trans1, trans2){
    const m1 = [trans1[0], trans1[3], trans1[6], trans1[1], trans1[4], trans1[7], trans1[2], trans1[5], trans1[8]];
    const m2 = [trans2[0], trans2[3], trans2[6], trans2[1], trans2[4], trans2[7], trans2[2], trans2[5], trans2[8]];

    const t0 = (m2[0] * m1[0]) + (m2[1] * m1[3]) + (m2[2] * m1[6]); //
    const t1 = (m2[0] * m1[1]) + (m2[1] * m1[4]) + (m2[2] * m1[7]); //
    const t2 = (m2[0] * m1[2]) + (m2[1] * m1[5]) + (m2[2] * m1[8]); //

    const t3 = (m2[3] * m1[0]) + (m2[4] * m1[3]) + (m2[5] * m1[6]); //
    const t4 = (m2[3] * m1[1]) + (m2[4] * m1[4]) + (m2[5] * m1[7]); //
    const t5 = (m2[3] * m1[2]) + (m2[4] * m1[5]) + (m2[5] * m1[8]); //
    
    const t6 = (m2[6] * m1[0]) + (m2[7] * m1[3]) + (m2[8] * m1[6]); //
    const t7 = (m2[6] * m1[1]) + (m2[7] * m1[4]) + (m2[8] * m1[7]); //
    const t8 = (m2[6] * m1[2]) + (m2[7] * m1[5]) + (m2[8] * m1[8]); //

    const augmented_matrix = [t0, t3, t6, t1, t4, t7, t2, t5, t8];
    return augmented_matrix;
}

/*
// TO DO 1: Provides a 3x3 transformation matrix represented as an array containing 9 values arranged in column-major sequence.
// Initially, the transformation employs scaling, followed by rotation, and ultimately, translation.
// The specified rotation measurement is in degrees.
function GetTransform(positionX, positionY, rotation, scale) {
    const cosA = Math.cos(rotation * (Math.PI / 180));
    const sinA = Math.sin(rotation * (Math.PI / 180));
  
    // Calculate the transformation matrix
    const matrix = [
      scale * cosA, -scale * sinA, positionX,
      scale * sinA, scale * cosA, positionY,
      0, 0, 1
    ];
  
    //const matrix2 = [matrix[0][0], matrix[1][0], matrix[2][0], matrix[0][1], matrix[1][1], matrix[2][1], matrix[0][2], matrix[1][2], matrix[2][2]];

    const a = scale * cosA;
    const b = scale * -sinA;
    const c = positionX;
    const d = scale * sinA;
    const e = scale * cosA;
    const f = positionY;

    const matrix2 = [a, d, 0, b, e, 0, c, f, 1];
    //const matrix3 = matrix1*matrix2;

    return matrix;
    //return matrix3;
    //return matrix2;
}

// TO DO 2:Provides a 3x3 transformation matrix represented as an array containing 9 values arranged in column-major sequence.
// The inputs consist of transformation matrices following the identical format.
// The resulting transformation initially employs trans1 and subsequently applies trans2.
function ApplyTransform(trans1, trans2) {
  // Combine two 3x3 transformation matrices (trans1 and trans2)
  var result = [];

  for (var row = 0; row < 3; row++) {
    for (var col = 0; col < 3; col++) {
      var sum = 0;
      for (var i = 0; i < 3; i++) {
        sum += trans1[row + i * 3] * trans2[i + col * 3];
      }
      result.push(sum);
    }
  }

  return result;
}
*/