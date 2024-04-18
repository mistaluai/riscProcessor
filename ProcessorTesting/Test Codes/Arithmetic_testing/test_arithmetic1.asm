.data
.text
addi $1 , $0 , 5
addi $2 , $0 , 10
slt $3 , $2 , $1
beq $3 , $0 , else
addi $4 , $0 , 1
j label 
else:
addi $4 , $0 , 0
label:
jal exit
exit:
jr $7

 