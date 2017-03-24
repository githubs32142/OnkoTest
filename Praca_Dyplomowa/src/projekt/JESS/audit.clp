(clear)
(reset)

(deftemplate Point
(slot answer1 )
(slot answer2 )
(slot answer3 )
(slot answer4 )
(slot answer5 )
(slot answer6 )
(slot answer7 )
(slot answer8 )
(slot answer9 )
(slot answer10 )
)

(defrule Sum1-3
( Point (answer1 ?answer1 )(answer2 ?answer2 )(answer3 ?answer3 ) )
	=>
        (assert (Sum3 (+ ?answer1 ?answer2 ?answer3 )))
)

(defrule Sum1-3and7-10
( Point (answer1 ?answer1 )(answer2 ?answer2 )(answer3 ?answer3 ) (answer7 ?answer7 ) (answer8 ?answer8 ) (answer9 ?answer9 ) (answer10 ?answer10 ) )
	=>
        (assert (Sum6 (+ ?answer1 ?answer2 ?answer3 ?answer7 ?answer8 ?answer9 ?answer10 )))
)

(defrule Low-Level-Risc
	(Sum3 ?sum3 )(test (< ?sum3 8))
	=>
    ( printout t "Niski poziom ryzyka" crlf)
)

(defrule Risc-Level-Risc
	(Sum3 ?sum3 )(and(test (> ?sum3 7))(test (< ?sum3 15)) ) 
	=>
    ( printout t "Rysykowne spożywanie alkoholu" crlf)
)

(defrule Harmful-drinking-alcohol
	(Sum6 ?sum6 )(and(test (> ?sum6 15))(test (< ?sum6 20)) ) 
	=>
    ( printout t "Szkodliwe spożywanie alkoholu" crlf)
)

(defrule Addiction
	(Sum6 ?sum6 )(test (> ?sum6 19))  
	=>
    ( printout t "Podejżenie uzależnienia alkoholowego" crlf)
)

(facts)
(run)