create or replace view t_attachment_view as
select t_attachment.*,
       case
       when t_user.head_img_id is not null then 't_user.head_img_id'
       else null
       end link_info
from t_attachment t_attachment
left join t_user t_user on t_user.head_img_id = t_attachment.id;
