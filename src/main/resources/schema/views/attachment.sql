create or replace view t_attachment_view as
select t_attachment.*,
       case
       when t_user.head_img_id is not null then 't_user.head_img_id'
       when tmal.attachment_id is not null then 't_mail_attachment_link.attachment_id'
       when tosal.attachment_id is not null then 't_open_source_attachment_link.attachment_id'
       when tlal.attachment_id is not null then 't_letter_attachment_link.attachment_id'
       when tlral.attachment_id is not null then 't_letter_reply_attachment_link.attachment_id'
       else null
       end link_info
from t_attachment t_attachment
left join t_user t_user on t_attachment.id = t_user.head_img_id
left join t_mail_attachment_link tmal on t_attachment.id = tmal.attachment_id
left join t_open_source_attachment_link tosal on t_attachment.id = tosal.attachment_id
left join t_letter_attachment_link tlal on t_attachment.id = tlal.attachment_id
left join t_letter_reply_attachment_link tlral on t_attachment.id = tlral.attachment_id;
