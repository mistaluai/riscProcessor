.data
.text
#initialze registers
addi $1 , $0 , 10
addi $2 , $0 , 15
jal swap
j halt

swap:
add $3 , $1 , $0 #copies the first in temp reg
addi $1 , $2 , 0 #set the 1st to be equal the second
addi $2 , $3 , 0 #second to be equal the value of the first
jr $7

halt:
j halt

