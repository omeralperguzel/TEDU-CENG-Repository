.text
add $t1, $0, 0x7f6b2684
sw $t1, 0x10000000($0)
lb $t2, 0x10000002($0)
sub $t0, $t2, 2


#The code written below is used to exit the program or terminate the execution

li  $v0, 10           # service 1 is print integer
add $a0, $t0, $zero  # load desired value into argument register $a0, using pseudo-op
syscall
