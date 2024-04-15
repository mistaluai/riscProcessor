.data
fullColumn: .word 65535 0
column36: .word 36 1
column24: .word 24 2
.text
lw $1, 0($0)
draw $1, 0
draw $1, 15

loop:
#frame 1
addi $1, $0, 2
draw $1, 5
draw $1, 9
addi $1, $0, 5
draw $1, 6
draw $1, 7
draw $1, 8
#frame 2
lw $1, 1($0)
draw $1, 6
draw $1, 7
draw $1, 8
lw $1, 2($0)
draw $1, 5
draw $1, 9
#frame 3
lw $1, 1($0)
sll $1, $1, 1
draw $1, 6
draw $1, 7
draw $1, 8
lw $1, 2($0)
sll $1, $1, 1
draw $1, 5
draw $1, 9
#frame 4
lw $1, 1($0)
draw $1, 6
draw $1, 7
draw $1, 8
lw $1, 2($0)
draw $1, 5
draw $1, 9
#frame 5
addi $1, $0, 5
draw $1, 6
draw $1, 7
draw $1, 8
addi $1, $0, 2
draw $1, 5
draw $1, 9
j loop