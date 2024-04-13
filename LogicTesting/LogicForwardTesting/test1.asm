.data
.text 
addi $1, $1, 1
addi $2, $2, 0 


or $3, $2, $1   
andi $4, $1, 2  
nor $5, $1, $1  
xori $6, $1, 1


 
jal exit
exit:
addi $0 , $0 , 0
jr $7