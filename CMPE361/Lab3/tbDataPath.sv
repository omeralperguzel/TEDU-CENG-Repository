module tbDataPath();
logic clk, reset, memtoreg, pcsrc, alusrc, regdst, regwrite, jump;
logic [2:0] alucontrol;
logic zero;
logic [31:0] pc, instr, aluout, writedata, readdata;

datapath UT(clk, reset, memtoreg, pcsrc, alusrc, regdst,regwrite, jump, alucontrol, zero, pc, instr, aluout, writedata, readdata);

initial
begin
clk=1; reset=1; 
{regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'bxxxxxxxxx;
readdata=32'd54;
#5 clk=0; reset=0; instr=32'h2010000a; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b101000100; //addi $s0,$0,10  

#5 clk=1; 

#5 clk=0; instr=32'h2011000f; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b101000100; //addi $s1,$0,15  
#5 clk=1;

#5 clk=0; instr=32'h02114020; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b110000100; //add $t0, $s0,$s1
#5 clk=1; 

#5 clk=0; instr=32'h02304022; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b110001100;//sub $t0,$s1,$s0
#5 clk=1; 

#5 clk=0; instr=32'h02114024; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b110000000;//and $t0, $s0,$s1
#5 clk=1; 

#5 clk=0; instr=32'h02114025; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b110000010;//or $t0, $s0,$s1
#5 clk=1; 

#5 clk=0; instr=32'h8e09000a; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b101100100;//back: lw $t1, 10($s0) 100011,10000,01001,0000000000001010
#5 clk=1; 

#5 clk=0; instr=32'hae29000a; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b001000100;//next: sw $t1, 10($s1)
#5 clk=1; 

#5 clk=0; instr=32'h1211fffd; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b000001100;//beq $s0,$s1,back
#5 clk=1; 

#5 clk=0; instr=32'h2010000f; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b101000100;//addi $s0, $0,15
#5 clk=1; 

#5 clk=0; instr=32'h1211fffd; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b000011100;//beq $s0,$s1,back
#5 clk=1;

#5 clk=0; 
#5 clk=1; 

#5 clk=0; instr=32'h08100007; {regwrite,regdst,alusrc,memtoreg,pcsrc,alucontrol,jump}=9'b000010001;//j next
#5 clk=1;

#5 clk=0; 
#5 clk=1; 

 



end
endmodule
