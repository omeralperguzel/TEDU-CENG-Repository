//CMPE360 PROJECT7//
//----------------------------------------------------------------------------
// State Variable Setup 
//----------------------------------------------------------------------------

// This variable will store the WebGL rendering context
var gl;

//Collect shape information into neat package
var shapes = {
   wireCube: {points:[], colors:[], start:0, size:0, type: 0},
   solidCube: {points:[], colors:[], start:0, size:0, type: 0},
   axes: {points:[], colors:[], start:0, size:0, type: 0},
};

//Variables for Transformation Matrices
var mv = new mat4();
var p  = new mat4();
var mvLoc, projLoc;

//Model state variables
var shoulder = 0, elbow = 0;


//----------------------------------------------------------------------------
// Define Shape Data 
//----------------------------------------------------------------------------

//Some colours
var red = 		   	vec4(1.0, 0.0, 0.0, 1.0);
var green = 	   	vec4(0.0, 1.0, 0.0, 1.0);
var blue = 		   	vec4(0.0, 0.0, 1.0, 1.0);
var lightred =		vec4(1.0, 0.5, 0.5, 1.0);
var lightgreen =	vec4(0.5, 1.0, 0.5, 1.0);
var lightblue =   	vec4(0.5, 0.5, 1.0, 1.0);
var white = 	   	vec4(1.0, 1.0, 1.0, 1.0);


//Generate Axis Data: use LINES to draw. Three axes in red, green and blue
shapes.axes.points = 
[ 
	vec4(  2.0,  0.0,  0.0, 1.0), //x axis, will be green
	vec4( -2.0,  0.0,  0.0, 1.0),
	vec4(  0.0,  2.0,  0.0, 1.0), //y axis, will be red
	vec4(  0.0, -2.0,  0.0, 1.0),
	vec4(  0.0,  0.0,  2.0, 1.0), //z axis, will be blue
	vec4(  0.0,  0.0, -2.0, 1.0)
];

shapes.axes.colors = 
[
	green,green,
	red,  red,
	blue, blue
];


//Define points for a unit cube
var cubeVerts = [
	vec4( 0.5,  0.5,  0.5, 1), //0
	vec4( 0.5,  0.5, -0.5, 1), //1
	vec4( 0.5, -0.5,  0.5, 1), //2
	vec4( 0.5, -0.5, -0.5, 1), //3
	vec4(-0.5,  0.5,  0.5, 1), //4
	vec4(-0.5,  0.5, -0.5, 1), //5
	vec4(-0.5, -0.5,  0.5, 1), //6
	vec4(-0.5, -0.5, -0.5, 1), //7
];

//Look up patterns from cubeVerts for different primitive types
//Wire Cube - draw with LINE_STRIP
var wireCubeLookups = [
	0,4,6,2,0, //front
	1,0,2,3,1, //right
	5,1,3,7,5, //back
	4,5,7,6,4, //right
	4,0,1,5,4, //top
	6,7,3,2,6, //bottom
];

//Solid Cube - draw with TRIANGLES, 2 triangles per face
var solidCubeLookups = [
	0,4,6,   0,6,2, //front
	1,0,2,   1,2,3, //right
	5,1,3,   5,3,7,//back
	4,5,7,   4,7,6,//left
	4,0,1,   4,1,5,//top
	6,7,3,   6,3,2,//bottom
];

//Expand Wire Cube data: this wire cube will be white...
for (var i =0; i < wireCubeLookups.length; i++)
{
   shapes.wireCube.points.push(cubeVerts[wireCubeLookups[i]]);
   shapes.wireCube.colors.push(white);
}
for (var i =0; i < secondCubeLookups.length; i++)
{
   shapes.secondCube.points.push(cubeVerts[secondCubeLookups[i]]);
   shapes.secondCube.colors.push(blue);
}

//Expand Solid Cube data: each face will be a different color so you can see
//    the 3D shape better without lighting.
var colorNum = 0;
var colorList = [lightblue, lightgreen, lightred, blue, red, green];
for (var i = 0; i < solidCubeLookups.length; i++)
{
   shapes.solidCube.points.push(cubeVerts[solidCubeLookups[i]]);
   shapes.solidCube.colors.push(colorList[colorNum]);
   if (i % 6 == 5) colorNum++; //Switch color for every face. 6 vertices/face
}

//load data into points and colors arrays - runs once as page loads.
var points = [];
var colors = [];

//Convenience function:
//  - adds shape data to points and colors arrays
//  - adds primitive type to a shape
function loadShape(myShape, type)
{
   myShape.start = points.length;
   points = points.concat(myShape.points);
   colors = colors.concat(myShape.colors);
   myShape.size = points.length - myShape.start;
   myShape.type = type;
}

//----------------------------------------------------------------------------
// Initialization Event Function
//----------------------------------------------------------------------------

window.onload = function init() {
  // Set up a WebGL Rendering Context in an HTML5 Canvas
  var canvas = document.getElementById("gl-canvas");
  gl = canvas.getContext("webgl2");
  if (!gl) {
   canvas.parentNode.innerHTML("Cannot get WebGL2 Rendering Context");
  }

  //  Configure WebGL
  //  eg. - set a clear color
  //      - turn on depth testing
  gl.viewport(0, 0, gl.drawingBufferWidth, gl.drawingBufferHeight);
  gl.enable(gl.DEPTH_TEST);
  gl.clearColor(0.0, 0.0, 0.0, 1.0);
  gl.enable(gl.CULL_FACE);

  //  Load shaders and initialize attribute buffers
  var program = initShaders(gl, "shader.vert", "shader.frag");
  gl.useProgram(program);

  // Set up data to draw
  // Mostly done globally in this program...
  loadShape(shapes.wireCube, gl.LINE_STRIP);
  loadShape(shapes.solidCube, gl.TRIANGLES);
  loadShape(shapes.axes, gl.LINES);
  
  // Load the data into GPU data buffers and
  // Associate shader attributes with corresponding data buffers
  //***Vertices***
  var vertexBuffer = gl.createBuffer();
  gl.bindBuffer( gl.ARRAY_BUFFER, vertexBuffer );
  gl.bufferData( gl.ARRAY_BUFFER,  flatten(points), gl.STATIC_DRAW );
  program.vPosition = gl.getAttribLocation(program, "vPosition");
  gl.vertexAttribPointer( program.vPosition, 4, gl.FLOAT, gl.FALSE, 0, 0 );
  gl.enableVertexAttribArray( program.vPosition );
 
  //***Colors***
  var colorBuffer = gl.createBuffer();
  gl.bindBuffer( gl.ARRAY_BUFFER, colorBuffer );
  gl.bufferData( gl.ARRAY_BUFFER,  flatten(colors), gl.STATIC_DRAW );
  program.vColor = gl.getAttribLocation(program, "vColor");
  gl.vertexAttribPointer( program.vColor, 4, gl.FLOAT, gl.FALSE, 0, 0 );
  gl.enableVertexAttribArray( program.vColor );

  // Get addresses of shader uniforms
  projLoc = gl.getUniformLocation(program, "p");
  mvLoc = gl.getUniformLocation(program, "mv");

  //Set up viewport - see WebGL Anti-Patterns link
  //gl.viewport(0, 0, gl.drawingBufferWidth, gl.drawingBufferHeight);

  //Set up projection matrix
  p = perspective(45.0, 1.0, 0.1, 100.0);
  gl.uniformMatrix4fv(projLoc, gl.FALSE, flatten(transpose(p)));
  //gl.uniformMatrix4fv(mvLoc, gl.TRUE, flatten(transpose(mv)));
  //gl.drawArrays(shapes.wireCube.type, shapes.wireCube.start, shapes.wireCube.size);

  requestAnimationFrame(render);
};



//----------------------------------------------------------------------------
// Rendering Event Function
//----------------------------------------------------------------------------

function render() {

	gl.clear(gl.DEPTH_BUFFER_BIT | gl.COLOR_BUFFER_BIT);
	
	//Set initial view
	var eye = vec3(0.0, 0.0, 10.0);
	var at =  vec3(0.0, 0.0, 0.0);
	var up =  vec3(0.0, 1.0, 0.0);

   var mv = mat4();
   var mv2 = mat4();
   mv2 = lookAt(eye,at,up);

	mv = 
   lookAt(eye,at,up);
	//translate(0.0, 0.0, -15.0);
	//wireCube(0,0,0,0,0,0); 
   mv = mult(mv, translate(1.0, 0.0, 0.0));  
   mv2 = mult(mv, rotate(45, [0, 1, 0]));


	gl.uniformMatrix4fv(mvLoc, gl.TRUE, flatten(transpose(mv)));
   gl.drawArrays(shapes.wireCube.type, shapes.wireCube.start, shapes.wireCube.size);

   gl.uniformMatrix4fv(mvLoc, gl.TRUE, flatten(transpose(mv2)));
   gl.drawArrays(shapes.wireCube2.type, shapes.wireCube2.start, shapes.wireCube2.size);


}



















