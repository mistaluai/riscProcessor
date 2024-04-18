.data
barframe1: .word 960 0
ballframe1: .word 128 1
barframe2: .word 3840 2
ballframe2: .word 256 3
barframe3: .word 7680 4
ballframe5: .word 2048 5
barframe6: .word 30720 6
ballframe7: .word 8192 7
secondbarframe11: .word 240 8
.text
loop:
#frame 1
lw $1, 0($0)
draw $1, 0
draw $1, 15
lw $1, 1($0)
draw $1, 8
#frame 2
lw $1, 2($0)
draw $1, 0
lw $1, 3($0)
draw $1, 7
draw $0, 8
#frame3
sll $1, $1, 1
draw $1, 6
draw $0, 7
#frame4
sll $1, $1, 1
draw $1, 5
draw $0, 6
lw $1, 4($0)
draw $1, 0
#frame5
lw $1, 5($0)
draw $1, 4
draw $0, 5
#frame6
sll $1, $1, 1
draw $1, 3
draw $0, 4
lw $1, 6($0)
draw $1, 0
#frame7
lw $1, 7($0)
draw $1, 2
draw $0, 3
#frame8
sll $1, $1, 1
draw $1, 1
draw $0, 2
#frame9
lw $1, 6($0)
draw $1, 0
lw $1, 7($0)
draw $1, 2
draw $0, 1
#frame10
srl $1, $1, 1
draw $1, 3
draw $0, 2
#frame11
srl $1, $1, 1
draw $1, 4
draw $0, 3
lw $2, 8($0)
draw $2, 15
#frame12
srl $1, $1, 1
draw $1, 5
draw $0, 4
#frame13
srl $1, $1, 1
draw $1, 6
draw $0, 5
#frame14
srl $1, $1, 1
draw $1, 7
draw $0, 6
#frame15
srl $1, $1, 1
draw $1, 8
draw $0, 7
#frame16
srl $1, $1, 1
draw $1, 9
draw $0, 8
srl $2, $2, 2
draw $2, 15
#frame17
srl $1, $1, 1
draw $1, 10
draw $0, 9
#frame18
srl $1, $1, 1
draw $1, 11
draw $0, 10
#frame19
srl $1, $1, 1
draw $1, 12
draw $0, 11
#frame20
srl $1, $1, 1
draw $1, 13
draw $0, 12
#frame21
srl $1, $1, 1
draw $1, 14
draw $0, 13
srl $2, $2, 2
draw $2, 15
#frame22
sll $1, $1, 1
draw $1, 13
draw $0, 14
#frame16
sll $1, $1, 1
draw $1, 12
draw $0, 13
sll $2, $2, 2
draw $2, 15
#frame17
sll $1, $1, 1
draw $1, 11
draw $0, 12
#frame18
sll $1, $1, 1
draw $1, 10
draw $0, 11
#frame19
sll $1, $1, 1
draw $1, 9
draw $0, 10
#frame20
sll $1, $1, 1
draw $1, 8
draw $0, 9
#frame21
sll $2, $2, 2
draw $2, 15
j loop