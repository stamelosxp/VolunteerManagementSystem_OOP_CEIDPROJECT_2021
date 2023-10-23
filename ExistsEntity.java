public class ExistsEntity{
    public static int  existEntity(Material m, Organization o) throws ExistsEntityException{
        if(!(o.entityList.contains(m))){
            throw new ExistsEntityException("This material is not exist in this Organization!");
        }
        return m.getId();
    }
    public static int  existEntity(Service s, Organization o) throws ExistsEntityException{
        if(!(o.entityList.contains(s))){
            throw new ExistsEntityException("This service is not exist in this Organization!");
        }
        return s.getId();
    }

    public static int existsEntity(int id, Organization o) throws ExistsEntityException{
        for (int i=0; i<o.entityList.size(); i++){
            int checkId=o.entityList.get(i).getId();
            if(!(checkId == id)){
                throw new ExistsEntityException("This entity is not exist in organization!");
            }
        }
        return id;
    }
    public static int existsMaterial(int id, Organization o) throws ExistsEntityException{
        for (int i=0; i<o.entityList.size(); i++){
            int checkId=o.entityList.get(i).getId();
            if(checkId == id){
                if(!(o.entityList.get(i).typeOfEntity().compareTo("Material")==0)){
                    throw new ExistsEntityException("This material is not exist in organization!");
                }
                break;
            }
            else {
                throw new ExistsEntityException("This material is not exist in organization!");
            }
        }
        return id;
    }

    public static int existsService(int id, Organization o) throws ExistsEntityException{
        for (int i=0; i<o.entityList.size(); i++){
            int checkId=o.entityList.get(i).getId();
            if(checkId == id){
                if(!(o.entityList.get(i).typeOfEntity().compareTo("Service")==0)){
                    throw new ExistsEntityException("This service is not exist in organization!");
                }
                else {
                    break;
                }
            }
            else {
                throw new ExistsEntityException("This service is not exist in organization!");
            }
        }
        return id;
    }


    public static String existDonEntity(Entity e, Donator d, Organization o) throws ExistsEntityException{
        for(int i=0; i<d.getOffersList().size(); i++){
            if(!(d.getOffersList().get(i).getE() == e)){
               throw new ExistsEntityException("This entity is not exists in offers list of Donator!");
            }
        }
        return e.getName();
    }

    public  static String existBenEntity(Entity e, Beneficiary b, Organization o) throws ExistsEntityException{
        for (int i=0;i<b.getRequestsList().size(); i++){
            if(!(b.getRequestsList().get(i).getE() == e)){
                throw new ExistsEntityException("This entity is not exists in requests list of beneficiary!");
            }
        }
        return e.getName();
    }
}
