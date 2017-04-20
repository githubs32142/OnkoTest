(clear)
(reset)

(deftemplate Point
(slot sum )
)


(defrule rule1
	 (> sum 40)
	=>
    ( printout t "1Zbyt duże spożywanie błonnika." crlf)
)
(defrule rule2
	 (< sum 20)
	=>
    ( printout t "1Zbyt małe spożywanie błonnika." crlf)
)
(defrule rule3
	(and  (> sum 19.99) (< sum 39.99) )
	=>
    ( printout t "Poprawne spożywanie błonnika." crlf)
)

(facts)
(run)