package JCF;

import java.util.*;

import static java.util.Comparator.reverseOrder;

public class ExerciciosExame_Q1_JCF {

    /**
     * Normal 2020
     * 1. Elabore um método que coloca numa lista de objetos Pair<Key,Value> a fusão ordenada de duas outras listas
     * de objetos Pair<Key,Value>, também ordenadas. Os elementos de valor igual nas duas listas deverão ser
     * colocados alternadamente na lista resultado. Por exemplo:
     * Lista A: [<A,2>, <A,2>, <A,5>] Lista B: [<B,1>, <B,1>, <B,2>, <B,3>, <B,4>, <B,4>, <B,5>]
     * Lista Resultado: [<B,1>, <B,1>, <A,2>, <B,2>, <A,2>, <B,3>, <B,4>, <B,4>, <A,5>, <B,5>]
     * Considere a seguinte assinatura
     * public static<K, E extends Comparable<E>> List<Pair<K,E>>
     * mergeLists(List<Pair<K,E>> A, List<Pair<K,E>> B)
     */
    public static<K, E extends Comparable<E>> List<Map.Entry<K,E>> mergeLists(List<Map.Entry<K,E>> A, List<Map.Entry<K,E>> B) {
        List<Map.Entry<K,E>> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.size() && j < B.size()) {
            Map.Entry<K,E> a = A.get(i);
            Map.Entry<K,E> b = B.get(j);

            if (a.getValue().compareTo(b.getValue()) < 0) {
                result.add(a);
                i++;

            } else if (a.getValue().compareTo(b.getValue()) > 0) {
                result.add(b);
                j++;

            } else {
                result.add(a);
                result.add(b);
                i++;
                j++;
            }
        }

        for(i=i; i < A.size();i++)
            result.add(A.get(i));
        for(j=j; j < B.size();j++)
            result.add(B.get(j));
        return result;
    }

    /**
     * Exame Normal 2022
     1. Implemente um método que dada uma lista de inteiros ordenados por ordem crescente e um valor inteiro
     relativo à amplitude do(s) intervalo(s), devolva um map com o(s) intervalo(s) contidos na lista e respetivos
     valores da lista nesse intervalo. Só devem ser considerados os intervalos cujos extremos pertençam à lista e
     cada valor contido no intervalo não pode ser repetido noutro intervalo. Por exemplo, a tabela abaixo contém
     a lista a considerar e o resultado para algumas amplitudes: (...)

     Ex. Lista = [1, 2, 3, 3, 4, 6, 8, 9]
     Amplitude = 2
     Resultado = {[1,3] -> [1, 2, 3, 3], [4,6] -> [4, 6]}

     Map<String, List<Integer>> retornaListaAmplitude (int amplitude, List<Integer> l)
     **/
    public static Map<String, List<Integer>> retornaListaAmplitude(int amplitude, List<Integer> l){

        Map<String, List<Integer>> map = new HashMap<>();

        int i = 0;
        while (i < l.size()) {
            int beggining = l.get(i);
            int end = beggining + amplitude;

            if (end > l.get(l.size() - 1)) {
                break;
            }

            List<Integer> lista = new ArrayList<>();

            int j = i;
            while (j < l.size()) {
                if (l.get(j) <= end) {
                    lista.add(l.get(j));
                    j++;
                } else {
                    i = j - 1;
                    break;
                }
            }
            map.put("[" + beggining + "," + end + "]", lista);
            i++;
        }
        return map;
    }

    /**
     * Exame - Recurso 2022
     1. Considere uma lista de objetos Map.Entry<Integer, String> que contem os países vencedores da Football
     World Cup por edição, por exemplo: [(1930,"Uruguay"), (1934,"Italy"), (1938,"Italy"), (1950,"Uruguay"),...,(2006,
     "Italy"), (2010,"Spain"), ...]. Desenvolva um método que devolve num map cada país e a respetiva lista dos
     anos em que foi vencedor da taça. A lista dos anos deve estar ordenada decrescentemente. Para a lista de
     objetos Map.Entry acima deve devolver Uruguay -> [1950, 1930]; Italy -> [2006, 1938, 1934]; Spain ->
     [2010].

     public static Map<String, List<Integer>> footWorldCup
     (List<Map.Entry<Integer,String>> lf)
     **/
    public static Map<String, List<Integer>> footWorldCup(List<Map.Entry<Integer, String>> paisesVencedores){

        Map<String, List<Integer>> invertedMap = new HashMap<>();

        for (Map.Entry<Integer, String> map : paisesVencedores){ //para cada um dos pares

            Integer mapYear = map.getKey();
            String mapCountry = map.getValue();

            if ( invertedMap.containsKey(mapCountry) ){
                invertedMap.get(mapCountry).add(mapYear);
                invertedMap.get(mapCountry).sort(reverseOrder());
            }
            else{
                //criar uma lista com o ano, do maior para o menor
                List<Integer> a = new ArrayList<>();
                a.add(mapYear);
                invertedMap.put(mapCountry, a);
            }
        }

        return invertedMap;
    }


    /**
     * Exame Especial 22
     * Considere a seguinte class Student e respetivos métodos de acesso às variáveis.
     * Implemente um método que recebe uma lista de objetos Student e devolve num map a média das notas de
     * exame agregada por turma.
     * Map<String, Double> avScoreClass (List<Student> ls)
     */
    public static class Student {
        String student_id;
        // id do estudante
        String class_id;
        // id da turma
        Double exam_score;
        // nota de exame
        Double freq_score;
        // nota de frequência
        private boolean [] presences; // identifica se um aluno teve presente na lista de aulas da UC

        String getClassId(){return class_id;}
        Double getExamScore() {return exam_score;}

        Student(String class_id, Double exam_score){
            this.class_id = class_id;
            this.exam_score = exam_score;
        }
    }

    static Map<String, Double> avScoreClass(List<Student> ls){
        Map<String, Double> map = new HashMap<>();
        Map<String, Integer> nrStu = new HashMap<>();

        for (Student stu : ls){
            String classId = stu.getClassId();
            Double score = stu.getExamScore();

            if (!map.containsKey(classId)){
                map.put(classId, score);
                nrStu.put(classId, 1);
            }

            else{
                Double currentAvg = map.get(classId);
                int nrStudentsInClass = nrStu.get(classId);
                Double newAvg = (currentAvg * nrStudentsInClass + score)/(nrStudentsInClass + 1);
                map.put(classId, newAvg);
                nrStu.put(classId, nrStudentsInClass + 1);
            }
        }
        return map;
    }

    /**
     * Exame Normal 2023
     *
     */
    public class Mensagem {
        String emailRemetente;
        Set<String> lst_destinatarios;
        String assunto;
        String texto;

        String getRemetente() {return emailRemetente;}

        Set<String> getDestinatarios() {return lst_destinatarios;}
    }

    public class Servidor {
        List<Mensagem> caixaIn;
        List<Mensagem> caixaOut;

        public List<Mensagem> getCaixaIn() {
            return caixaIn;
        }

        public List<Mensagem> getCaixaOut() {
            return caixaOut;
        }

        public Map<String, Map<String, Integer>> numberMessagesSent() {
            // Devolve um map com o número de mensagens enviadas por cada utilizador, para cada destinatário.

            Map<String, Map<String, Integer>> map = new HashMap<>();
            //Remetente -> Destinatario -> Numero de mensagens
            List<Mensagem> caixaOut = getCaixaOut();

            //Isto foi como fiz no exame, mas foi um bocado à trolha
            for (Mensagem m : caixaOut) {
                String remetente = m.getRemetente();
                Set<String> destinatarios = m.getDestinatarios();
                if (map.containsKey(remetente)) {
                    Map<String, Integer> mapDestinatarios = map.get(remetente);
                    for (String destinatario : destinatarios) {
                        if (mapDestinatarios.containsKey(destinatario)) {
                            mapDestinatarios.put(destinatario, mapDestinatarios.get(destinatario) + 1);
                        } else {
                            mapDestinatarios.put(destinatario, 1);
                        }
                    }
                } else {
                    Map<String, Integer> mapDestinatarios = new HashMap<>();
                    for (String destinatario : destinatarios) {
                        mapDestinatarios.put(destinatario, 1);
                    }
                    map.put(remetente, mapDestinatarios);
                }
            }
            return map;
        }
    }

    public static void main(String[] args) {

        System.out.println("Exame Normal 2022");

        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(3);
        l.add(4);
        l.add(6);
        l.add(8);
        l.add(9);
        System.out.println(retornaListaAmplitude(2, l));


        System.out.println("Exame 2022-02-24 - Recurso 22\n");

        System.out.println(" Questão 1");
        Map.Entry<Integer, String> a = new AbstractMap.SimpleEntry<>(1930, "Uruguay");
        Map.Entry<Integer, String> b = new AbstractMap.SimpleEntry<>(1934, "Italy");
        Map.Entry<Integer, String> c = new AbstractMap.SimpleEntry<>(1938, "Italy");
        Map.Entry<Integer, String> d = new AbstractMap.SimpleEntry<>(1950, "Uruguay");
        Map.Entry<Integer, String> f = new AbstractMap.SimpleEntry<>(1958, "Brazil");
        Map.Entry<Integer, String> g = new AbstractMap.SimpleEntry<>(1962, "Brazil");
        Map.Entry<Integer, String> h = new AbstractMap.SimpleEntry<>(1966, "England");
        Map.Entry<Integer, String> i = new AbstractMap.SimpleEntry<>(1970, "Brazil");

        System.out.println(footWorldCup(Arrays.asList(a,b,c,d,f,g,h,i)));

        System.out.println();



        System.out.println("Exame 2022-09-08 - Especial 22\n");

        System.out.println(" Questão 1");
        Student st1 = new Student("1", 10.0);
        Student st2 = new Student("1", 16.0);
        Student st3 = new Student("2", 10.0);
        Student st4 = new Student("2", 15.0);
        Student st5 = new Student("2", 20.0);

        List<Student> list = new ArrayList<>();
        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);
        list.add(st5);

        System.out.println(
                avScoreClass (list)
        );

        System.out.println("Exame - Normal 20\n");
        //* Lista A: [<A,2>, <A,2>, <A,5>] Lista B: [<B,1>, <B,1>, <B,2>, <B,3>, <B,4>, <B,4>, <B,5>]
        //* Lista Resultado: [<B,1>, <B,1>, <A,2>, <B,2>, <A,2>, <B,3>, <B,4>, <B,4>, <A,5>, <B,5>]

        List<Map.Entry<String, Integer>> listaA = new ArrayList<>();
        List<Map.Entry<String, Integer>> listaB = new ArrayList<>();

        listaA.add(new AbstractMap.SimpleEntry<>("A", 2));
        listaA.add(new AbstractMap.SimpleEntry<>("A", 2));
        listaA.add(new AbstractMap.SimpleEntry<>("A", 5));

        listaB.add(new AbstractMap.SimpleEntry<>("B", 1));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 1));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 2));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 3));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 4));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 4));
        listaB.add(new AbstractMap.SimpleEntry<>("B", 5));

        List<Map.Entry<String, Integer>> listaResultado = mergeLists(listaA, listaB);

        System.out.println("Lista A: " + listaA);
        System.out.println("Lista B: " + listaB);

        System.out.println("Lista Resultado: " + listaResultado);
    }
}
