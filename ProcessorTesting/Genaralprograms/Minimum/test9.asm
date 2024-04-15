.data 
.text

addi $1, $1, 8
addi $2, $2, 14
addi $3, $3, 1
addi $6, $6, 0

blt $3, $2, minimum1
blt $2,$1, minimum2


minimum1:
blt $3, $1, minimum3
add $6, $6, $1
j exit


minimum2:
add $6, $6, $2
j exit

minimum3:
add $6, $6, $3
j exit



exit:
addi $0 , $0 , 0
j exit
