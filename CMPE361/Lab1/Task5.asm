.text
add $t0, $0, 0xff00ff00
sw $t0, 0x10000005($0)


#The code written below is used to exit the program or terminate the execution

li  $v0, 10           # service 1 is print integer
add $a0, $t0, $zero  # load desired value into argument register $a0, using pseudo-op
syscall
