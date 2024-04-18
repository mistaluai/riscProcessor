.data

.text
addi $1 , $0 , 5
addi $2 , $0 , 10
addi $3 , $0 ,  15
jal sum

j label

sum:
add $4 , $1 , $2
add $4 , $4 , $3
jr $7  

label:
jal exit
exit:
addi $0 , $0 , 0
jr $7