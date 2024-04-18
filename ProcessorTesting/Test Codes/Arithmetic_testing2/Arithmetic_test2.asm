.data

.text

addi $1 , $0 , 5
addi $2 , $0 , 10
addi $3 , $0 , 15
sub $4 , $3 , $2
addi $1 , $1 , 1
slt $5 , $4 , $1
beq $5, $0 , else
addi $6, $0 , 1
j label
else:
addi $6 , $0 , 0
label:
jal exit
exit:
jr $7

