.text

add $t0, $0, 10 # put 10 in +to
add $t1, $0, 20 # put 20 in $t1
add $t0, $t0, $t1 #add $t0 and $t1
add $t2, $0, 5 # put 5 in $t2
sub $t0, $t0, $t2 #subtract $t2 from $t0

#The code written below is used to exit the program or terminate the execution

li  $v0, 10           # service 1 is print integer
add $a0, $t0, $zero  # load desired value into argument register $a0, using pseudo-op
syscall