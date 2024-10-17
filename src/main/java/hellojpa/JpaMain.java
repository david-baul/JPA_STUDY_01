package hellojpa;

import org.h2.tools.ChangeFileEncryption;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //code
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try{
            Member member = new Member();
            member.setUsername("홍길동"); // 이 시점에는 db에 홍길동이 안들어가있다

            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER", Member.class)
                    .getResultList();

            em.flush();

            // flush 는 commit 과 query가 날아갈때 작동한다
            // 허나 dbconnection을 불러와서 자체적으로 작업시에는
            // jpa와 관련이 없기에 쿼리가 날아갈때 flush가 작동하지않는다
            // ex : dbconn.executeQuery("select * from member"); 결과값 0
            //결론 : query 작성후 em.flush()를 습관적으로 넣어주자

            for (Member member1 : resultList) {
                System.out.println("member1.getUsername() = " + member1.getUsername());
            }

            ts.commit();


/*
            //Criteria 사용준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();

            ts.commit();
*/
/*
            List<Member> resultList = em.createQuery(
                    "select m from Member m where m.username like '%kim%'"
                    , Member.class
            ).getResultList();

            ts.commit();
*/

/*
            Member member = new Member();
            member.setUsername("홍길동");
            member.setHomeAddress(new Address("서울","종로","1000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("소고기");

            member.getAddressHistory().add(new AddressEntity("서울1","종로1","2000"));
            member.getAddressHistory().add(new AddressEntity("서울2","종로2","3000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============================");

            Member findMember  = em.find(Member.class, member.getId());
            ts.commit();



            //서울 -> new서울
            Address findHomeAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("new서울",findHomeAddress.getStreet(),findHomeAddress.getZipcode()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new Address("서울1","종로1","2000"));
            findMember.getAddressHistory().add(new Address("new서울1","종로1","2000"));
            ts.commit();

 */
/*
            Address Address1 = new Address("city1","street1","zipcode1");
            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(Address1);
            em.persist(member1);

            Address Address2 = new Address("city2", "street2","zipcode2");
            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(Address2);
            em.persist(member2);

            member1.getHomeAddress().setCity("newCity1");

            Member member3 = new Member();
            member3.setHomeAddress(new Address("city3","street3","zipcode3"));

            member3.getHomeAddress().setStreet("netStreet3");
            em.persist(member3);
            ts.commit();
*/
        }catch (Exception e){
            ts.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
