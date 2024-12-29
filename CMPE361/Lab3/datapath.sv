module datapath(
input logic clk, reset,
input logic memtoreg, pcsrc,
input logic alusrc, regdst,
input logic regwrite, jump,
input logic [2:0] alucontrol,
output logic zero,
output logic [31:0] pc,
input logic [31:0] instr,
output logic [31:0] aluout, writedata,
input logic [31:0] readdata);

//complete the module

endmodule
