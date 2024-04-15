.data
.text 
addi $1 , $0 , 10
addi $2 , $0 , 15
jal swap
j label
swap:
add $3 , $1 , $0
addi $1 , $2 , 0
addi $2 , $3 , 0
jr $7

label:
jal exit
exit:
addi $0 , $0 , 0
jr $7 
