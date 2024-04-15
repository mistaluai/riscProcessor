.data
.text

addi $1 , $0 , 10
addi $2 , $0 , 5
addi $3 , $0  , 15

max:
blt $1 , $2 , L
blt $1 , $3 , L2
add $4 , $1 , $0
j end
L:
blt $2 , $3 , L2
add $4 , $2 , $0
j end
L2:
add $4 , $3 , $0

end:
jal exit
exit:
addi $0 , $0 , 0
jr $7

 