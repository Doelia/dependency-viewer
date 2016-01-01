package main

import (
	"flag"
	"fmt"
	"log"
	"net/http"
	"os/exec"

	"github.com/kardianos/osext"
)

var port = flag.Int("port", 2000, "Modifie le port d'écoute (défaut 2000)")
var jar = flag.String("jar", "/Users/doelia/Documents/dev/M2/M2-evolution/eclipse/ast-parser/graph-generator.jar", "Chemin du jar")

// StartWebServer Démarrage du serveur web (http + websockets)
func StartWebServer(port int) {
	fmt.Printf("Serveur web en écoute sur le port %d.\n", port)
	http.Handle("/", http.FileServer(http.Dir("../../web")))
	http.HandleFunc("/process", handlerProcess)
	if err := http.ListenAndServe(fmt.Sprintf(":%d", port), nil); err != nil {
		fmt.Println("Erreur à la création du serveur HTTP : ", err.Error())
	}
}

func handlerProcess(w http.ResponseWriter, req *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	methode := req.URL.Query().Get("methode")
	path := req.URL.Query().Get("path")
	classe := req.URL.Query().Get("classe")

	filename, _ := osext.Executable()
	fmt.Println("filename = " + filename)
	fmt.Println("Query " + methode + " " + path + " " + classe)

	out, err := exec.Command("java", "-jar", *jar, path, methode, classe).Output()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Out :\n%s\n", out)

	// content := out
	// fmt.Println("Content : " + content)
	w.Write([]byte(out))

}

func main() {
	flag.Parse()
	fmt.Println("=== AST WEB SERVER ===")
	StartWebServer(*port)

}
