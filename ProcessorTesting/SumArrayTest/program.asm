.data
arraySize: .word 30 0
.text
main:
	jal initialize_array
	jal sum_array
	jal halt_sim

initialize_array:
	add $1, $0, $0 #initialize counter
	lw $2, 0($0)
	init_loop:
		sw $1, 1($1)
		addi $1, $1, 1
		blt $1, $2, init_loop
	jr $7
sum_array:
	add $1, $0, $0 #initialize counter
	lw $2, 0($0)
	add $4, $0, $0 #initialize output register
	sum_loop:
		lw $3, 1($1)
		add $4, $4, $3
		addi $1, $1, 1
		blt $1, $2, sum_loop
	jr $7
halt_sim:
	j halt_sim
		